package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
import com.internationalairport.airportmanagementsystem.entities.Parking;
import com.internationalairport.airportmanagementsystem.service.interfaces.ParkingService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ParkingRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ParkingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @Autowired
    private ObjectMapper objectMapper;

    private Parking parking;
    private PostParkingDto postParkingDto;
    private PutParkingDto putParkingDto;

    @BeforeEach
    public void init() {
        parking = new Parking("Location1", 100, 20.0);
        parking.setParkingId(1);

        postParkingDto = new PostParkingDto("Location1", 100, 20.0);

        putParkingDto = new PutParkingDto(1, "Location1", 100, 20.0);
    }

    @Test
    public void ParkingRestController_CreateParking_ReturnCreated() throws Exception {
        given(parkingService.save(ArgumentMatchers.any(PostParkingDto.class))).willReturn(parking);

        ResultActions response = mockMvc.perform(post("/api/private/parkings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postParkingDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.parkingId", CoreMatchers.is(parking.getParkingId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(parking.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity", CoreMatchers.is(parking.getCapacity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", CoreMatchers.is(parking.getRate())));
    }

    @Test
    public void ParkingRestController_GetAllParkings_ReturnParkingList() throws Exception {
        given(parkingService.findAll()).willReturn(Arrays.asList(parking));

        ResultActions response = mockMvc.perform(get("/api/public/parkings")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].parkingId", CoreMatchers.is(parking.getParkingId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location", CoreMatchers.is(parking.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].capacity", CoreMatchers.is(parking.getCapacity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rate", CoreMatchers.is(parking.getRate())));
    }

    @Test
    public void ParkingRestController_GetParkingById_ReturnParking() throws Exception {
        int parkingId = 1;
        given(parkingService.findById(parkingId)).willReturn(parking);

        ResultActions response = mockMvc.perform(get("/api/public/parkings/{parkingId}", parkingId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.parkingId", CoreMatchers.is(parking.getParkingId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(parking.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity", CoreMatchers.is(parking.getCapacity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", CoreMatchers.is(parking.getRate())));
    }

    @Test
    public void ParkingRestController_UpdateParking_ReturnUpdatedParking() throws Exception {
        given(parkingService.save(ArgumentMatchers.any(PutParkingDto.class))).willReturn(parking);

        ResultActions response = mockMvc.perform(put("/api/private/parkings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putParkingDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.parkingId", CoreMatchers.is(parking.getParkingId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(parking.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity", CoreMatchers.is(parking.getCapacity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", CoreMatchers.is(parking.getRate())));
    }

    @Test
    public void ParkingRestController_DeleteParkingById_ReturnString() throws Exception {
        int parkingId = 1;
        given(parkingService.findById(parkingId)).willReturn(parking);
        doNothing().when(parkingService).deleteById(parkingId);

        ResultActions response = mockMvc.perform(delete("/api/private/parkings/{parkingId}", parkingId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Parking id - " + parkingId));
    }

    @Test
    public void ParkingRestController_DeleteAllParkings_ReturnString() throws Exception {
        String expectedResponse = "10 rows have been deleted";
        given(parkingService.deleteAll()).willReturn(expectedResponse);

        ResultActions response = mockMvc.perform(delete("/api/private/parkings")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }
}
