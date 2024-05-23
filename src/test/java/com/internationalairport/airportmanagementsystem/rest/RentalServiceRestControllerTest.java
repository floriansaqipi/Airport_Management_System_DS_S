package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRentalServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRentalServiceDto;
import com.internationalairport.airportmanagementsystem.entities.RentalService;
import com.internationalairport.airportmanagementsystem.service.interfaces.RentalServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RentalServiceRestController.class)
public class RentalServiceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalServiceService rentalServiceService;

    @InjectMocks
    private RentalServiceRestController rentalServiceRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAllRentalServices() throws Exception {
        RentalService rentalService1 = new RentalService("Car Rental", "Luxury car rental", 50.0);
        RentalService rentalService2 = new RentalService("Bike Rental", "Mountain bike rental", 20.0);

        given(rentalServiceService.findAll()).willReturn(Arrays.asList(rentalService1, rentalService2));

        mockMvc.perform(get("/api/public/rental_services")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Car Rental"))
                .andExpect(jsonPath("$[1].type").value("Bike Rental"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetRentalServiceById() throws Exception {
        RentalService rentalService = new RentalService("Car Rental", "Luxury car rental", 50.0);

        given(rentalServiceService.findById(1)).willReturn(rentalService);

        mockMvc.perform(get("/api/public/rental_services/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Car Rental"))
                .andExpect(jsonPath("$.description").value("Luxury car rental"))
                .andExpect(jsonPath("$.rate").value(50.0));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddRentalService() throws Exception {
        PostRentalServiceDto postRentalServiceDto = new PostRentalServiceDto("Car Rental", "Luxury car rental", 50.0);
        RentalService rentalService = new RentalService("Car Rental", "Luxury car rental", 50.0);

        given(rentalServiceService.save(any(PostRentalServiceDto.class))).willReturn(rentalService);

        mockMvc.perform(post("/api/private/rental_services")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"Car Rental\",\"description\":\"Luxury car rental\",\"rate\":50.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Car Rental"))
                .andExpect(jsonPath("$.description").value("Luxury car rental"))
                .andExpect(jsonPath("$.rate").value(50.0));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateRentalService() throws Exception {
        PutRentalServiceDto putRentalServiceDto = new PutRentalServiceDto(1, "Car Rental", "Luxury car rental updated", 60.0);
        RentalService rentalService = new RentalService("Car Rental", "Luxury car rental updated", 60.0);

        given(rentalServiceService.save(any(PutRentalServiceDto.class))).willReturn(rentalService);

        mockMvc.perform(put("/api/private/rental_services")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rentalId\":1,\"type\":\"Car Rental\",\"description\":\"Luxury car rental updated\",\"rate\":60.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Car Rental"))
                .andExpect(jsonPath("$.description").value("Luxury car rental updated"))
                .andExpect(jsonPath("$.rate").value(60.0));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteRentalServiceById() throws Exception {
        RentalService rentalService = new RentalService("Car Rental", "Luxury car rental", 50.0);

        given(rentalServiceService.findById(1)).willReturn(rentalService);
        doNothing().when(rentalServiceService).deleteById(1);

        mockMvc.perform(delete("/api/private/rental_services/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Rental Service id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteAllRentalServices() throws Exception {
        given(rentalServiceService.deleteAll()).willReturn("2 rows have been deleted");

        mockMvc.perform(delete("/api/private/rental_services")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("2 rows have been deleted"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetRentalServiceByIdNotFound() throws Exception {
        given(rentalServiceService.findById(1)).willThrow(new RuntimeException("Id not found - 1"));

        mockMvc.perform(get("/api/public/rental_services/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Id not found - 1", result.getResolvedException().getMessage()));
    }
}
