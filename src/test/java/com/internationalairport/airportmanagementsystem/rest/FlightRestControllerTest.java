package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class FlightRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightRestController flightRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(flightRestController).build();
    }

    @Test
    void testAddFlight() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        PostFlightDto postFlightDto = new PostFlightDto(
                "FL123",
                1,
                2,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1,
                List.of(1, 2, 3)
        );

        Flight savedFlight = new Flight("FL123", LocalDateTime.now(), LocalDateTime.now());

        when(flightService.save(any(PostFlightDto.class))).thenReturn(savedFlight);

        mockMvc.perform(post("/api/private/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postFlightDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNumber").value("FL123"));

        verify(flightService, times(1)).save(any(PostFlightDto.class));
    }

    @Test
    void testUpdateFlight() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        PutFlightDto putFlightDto = new PutFlightDto(
                1,
                "FL123",
                1,
                2,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1,
                List.of(1, 2, 3)
        );

        Flight updatedFlight = new Flight("FL123", LocalDateTime.now(), LocalDateTime.now());
        updatedFlight.setFlightId(1);

        when(flightService.save(any(PutFlightDto.class))).thenReturn(updatedFlight);

        mockMvc.perform(put("/api/private/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putFlightDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId").value(1))
                .andExpect(jsonPath("$.flightNumber").value("FL123"));

        verify(flightService, times(1)).save(any(PutFlightDto.class));
    }

    @Test
    void testGetFlightById() throws Exception {
        int flightId = 1;
        Flight flight = new Flight("FL123", LocalDateTime.now(), LocalDateTime.now());
        flight.setFlightId(flightId);

        when(flightService.findById(flightId)).thenReturn(flight);

        mockMvc.perform(get("/api/public/flights/{flightId}", flightId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightId").value(flightId))
                .andExpect(jsonPath("$.flightNumber").value("FL123"));

        verify(flightService, times(1)).findById(flightId);
    }

    @Test
    void testDeleteFlightById() throws Exception {
        int flightId = 1;
        Flight flight = new Flight("FL123", LocalDateTime.now(), LocalDateTime.now());
        flight.setFlightId(flightId);

        when(flightService.findById(flightId)).thenReturn(flight);

        mockMvc.perform(delete("/api/private/flights/{flightId}", flightId))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted flight with id - " + flightId));

        verify(flightService, times(1)).deleteById(flightId);
    }

    @Test
    void testDeleteAllFlights() throws Exception {
        when(flightService.deleteAll()).thenReturn("3 flights have been deleted");

        mockMvc.perform(delete("/api/private/flights"))
                .andExpect(status().isOk())
                .andExpect(content().string("3 flights have been deleted"));

        verify(flightService, times(1)).deleteAll();
    }
}