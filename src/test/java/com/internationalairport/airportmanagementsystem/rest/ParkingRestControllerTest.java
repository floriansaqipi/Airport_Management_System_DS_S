package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
import com.internationalairport.airportmanagementsystem.entities.Parking;
import com.internationalairport.airportmanagementsystem.service.interfaces.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParkingRestController.class)
public class ParkingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ParkingRestController(parkingService)).build();
    }

    @Test
    public void testFindAllParkings() throws Exception {
        Parking parking1 = new Parking("Location 1", 100, 10.0);
        Parking parking2 = new Parking("Location 2", 200, 15.0);

        Mockito.when(parkingService.findAll()).thenReturn(Arrays.asList(parking1, parking2));

        mockMvc.perform(get("/api/public/parkings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].location").value("Location 1"))
                .andExpect(jsonPath("$[1].location").value("Location 2"));
    }

    @Test
    public void testGetParkingServiceById() throws Exception {
        Parking parking = new Parking("Location 1", 100, 10.0);
        parking.setParkingId(1);

        Mockito.when(parkingService.findById(anyInt())).thenReturn(parking);

        mockMvc.perform(get("/api/public/parkings/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.location").value("Location 1"))
                .andExpect(jsonPath("$.capacity").value(100))
                .andExpect(jsonPath("$.rate").value(10.0));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddParking() throws Exception {
        Parking parking = new Parking("Location 1", 100, 10.0);
        parking.setParkingId(1);

        PostParkingDto postParkingDto = new PostParkingDto("Location 1", 100, 10.0);

        Mockito.when(parkingService.save(any(PostParkingDto.class))).thenReturn(parking);

        mockMvc.perform(post("/api/private/parkings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"location\": \"Location 1\", \"capacity\": 100, \"rate\": 10.0 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.location").value("Location 1"))
                .andExpect(jsonPath("$.capacity").value(100))
                .andExpect(jsonPath("$.rate").value(10.0));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateParking() throws Exception {
        Parking parking = new Parking("Location 1", 100, 10.0);
        parking.setParkingId(1);

        PutParkingDto putParkingDto = new PutParkingDto(1, "Location 1", 100, 10.0);

        Mockito.when(parkingService.save(any(PutParkingDto.class))).thenReturn(parking);

        mockMvc.perform(put("/api/private/parkings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"parkingId\": 1, \"location\": \"Location 1\", \"capacity\": 100, \"rate\": 10.0 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.location").value("Location 1"))
                .andExpect(jsonPath("$.capacity").value(100))
                .andExpect(jsonPath("$.rate").value(10.0));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteParkingById() throws Exception {
        Parking parking = new Parking("Location 1", 100, 10.0);
        parking.setParkingId(1);

        Mockito.when(parkingService.findById(1)).thenReturn(parking);
        Mockito.doNothing().when(parkingService).deleteById(1);

        mockMvc.perform(delete("/api/private/parkings/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Parking id - 1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteAllParkings() throws Exception {
        Mockito.when(parkingService.deleteAll()).thenReturn("2 rows have been deleted");

        mockMvc.perform(delete("/api/private/parkings"))
                .andExpect(status().isOk())
                .andExpect(content().string("2 rows have been deleted"));
    }
}
