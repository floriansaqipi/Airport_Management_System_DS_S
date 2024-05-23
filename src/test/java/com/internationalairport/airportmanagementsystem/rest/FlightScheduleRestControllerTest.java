package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
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

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightScheduleRestController.class)
public class FlightScheduleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightScheduleService flightScheduleService;

    @InjectMocks
    private FlightScheduleRestController flightScheduleRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAllFlightSchedules() throws Exception {
        FlightSchedule flightSchedule1 = new FlightSchedule(LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");
        FlightSchedule flightSchedule2 = new FlightSchedule(LocalDateTime.of(2023, 5, 23, 14, 0), LocalDateTime.of(2023, 5, 23, 16, 0), "Scheduled");

        given(flightScheduleService.findAll()).willReturn(Arrays.asList(flightSchedule1, flightSchedule2));

        mockMvc.perform(get("/api/public/flight_schedules")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("Scheduled"))
                .andExpect(jsonPath("$[1].status").value("Scheduled"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetFlightScheduleById() throws Exception {
        FlightSchedule flightSchedule = new FlightSchedule(LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");

        given(flightScheduleService.findById(1)).willReturn(flightSchedule);

        mockMvc.perform(get("/api/public/flight_schedules/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Scheduled"))
                .andExpect(jsonPath("$.scheduledDepartureTime").value("2023-05-22T10:00:00"))
                .andExpect(jsonPath("$.scheduledArrivalTime").value("2023-05-22T12:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddFlightSchedule() throws Exception {
        PostFlightScheduleDto postFlightScheduleDto = new PostFlightScheduleDto(1, LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");
        FlightSchedule flightSchedule = new FlightSchedule(LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");

        given(flightScheduleService.save(any(PostFlightScheduleDto.class))).willReturn(flightSchedule);

        mockMvc.perform(post("/api/private/flight_schedules")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"flightId\":1,\"scheduledDepartureTime\":\"2023-05-22T10:00:00\",\"scheduledArrivalTime\":\"2023-05-22T12:00:00\",\"status\":\"Scheduled\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Scheduled"))
                .andExpect(jsonPath("$.scheduledDepartureTime").value("2023-05-22T10:00:00"))
                .andExpect(jsonPath("$.scheduledArrivalTime").value("2023-05-22T12:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateFlightSchedule() throws Exception {
        PutFlightScheduleDto putFlightScheduleDto = new PutFlightScheduleDto(1, 1, LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");
        FlightSchedule flightSchedule = new FlightSchedule(LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");

        given(flightScheduleService.save(any(PutFlightScheduleDto.class))).willReturn(flightSchedule);

        mockMvc.perform(put("/api/private/flight_schedules")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"scheduleId\":1,\"flightId\":1,\"scheduledDepartureTime\":\"2023-05-22T10:00:00\",\"scheduledArrivalTime\":\"2023-05-22T12:00:00\",\"status\":\"Scheduled\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Scheduled"))
                .andExpect(jsonPath("$.scheduledDepartureTime").value("2023-05-22T10:00:00"))
                .andExpect(jsonPath("$.scheduledArrivalTime").value("2023-05-22T12:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteFlightScheduleById() throws Exception {
        FlightSchedule flightSchedule = new FlightSchedule(LocalDateTime.of(2023, 5, 22, 10, 0), LocalDateTime.of(2023, 5, 22, 12, 0), "Scheduled");

        given(flightScheduleService.findById(1)).willReturn(flightSchedule);
        doNothing().when(flightScheduleService).deleteById(1);

        mockMvc.perform(delete("/api/private/flight_schedules/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted flight schedule id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetFlightScheduleNotFound() throws Exception {
        given(flightScheduleService.findById(1)).willThrow(new RuntimeException("Flight schedule id not found - 1"));

        mockMvc.perform(get("/api/public/flight_schedules/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Flight schedule id not found - 1", result.getResolvedException().getMessage()));
    }
}
