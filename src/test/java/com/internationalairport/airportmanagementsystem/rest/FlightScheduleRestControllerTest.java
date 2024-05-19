package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
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

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = FlightScheduleRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class FlightScheduleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightScheduleService flightScheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    private FlightSchedule flightSchedule;
    private PostFlightScheduleDto postFlightScheduleDto;
    private PutFlightScheduleDto putFlightScheduleDto;

    @BeforeEach
    public void init() {
        flightSchedule = new FlightSchedule(LocalDateTime.of(2024, 5, 19, 10, 0),
                LocalDateTime.of(2024, 5, 19, 14, 0), "Scheduled");
        flightSchedule.setScheduleId(1);

        postFlightScheduleDto = new PostFlightScheduleDto(1, LocalDateTime.of(2024, 5, 19, 10, 0),
                LocalDateTime.of(2024, 5, 19, 14, 0), "Scheduled");

        putFlightScheduleDto = new PutFlightScheduleDto(1, 1, LocalDateTime.of(2024, 5, 19, 10, 0),
                LocalDateTime.of(2024, 5, 19, 14, 0), "Scheduled");
    }

    @Test
    public void FlightScheduleRestController_CreateFlightSchedule_ReturnOk() throws Exception {
        given(flightScheduleService.save(ArgumentMatchers.any(PostFlightScheduleDto.class))).willReturn(flightSchedule);

        ResultActions response = mockMvc.perform(post("/api/private/flight_schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postFlightScheduleDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId", CoreMatchers.is(flightSchedule.getScheduleId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduledDepartureTime", CoreMatchers.is(flightSchedule.getScheduledDepartureTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduledArrivalTime", CoreMatchers.is(flightSchedule.getScheduledArrivalTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(flightSchedule.getStatus())));
    }

    @Test
    public void FlightScheduleRestController_GetAllFlightSchedules_ReturnFlightScheduleList() throws Exception {
        when(flightScheduleService.findAll()).thenReturn(Arrays.asList(flightSchedule));

        ResultActions response = mockMvc.perform(get("/api/public/flight_schedules")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduleId", CoreMatchers.is(flightSchedule.getScheduleId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduledDepartureTime", CoreMatchers.is(flightSchedule.getScheduledDepartureTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduledArrivalTime", CoreMatchers.is(flightSchedule.getScheduledArrivalTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", CoreMatchers.is(flightSchedule.getStatus())));
    }

    @Test
    public void FlightScheduleRestController_GetFlightScheduleById_ReturnFlightSchedule() throws Exception {
        int flightScheduleId = 1;
        when(flightScheduleService.findById(flightScheduleId)).thenReturn(flightSchedule);

        ResultActions response = mockMvc.perform(get("/api/public/flight_schedules/{id}", flightScheduleId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId", CoreMatchers.is(flightSchedule.getScheduleId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduledDepartureTime", CoreMatchers.is(flightSchedule.getScheduledDepartureTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduledArrivalTime", CoreMatchers.is(flightSchedule.getScheduledArrivalTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(flightSchedule.getStatus())));
    }

    @Test
    public void FlightScheduleRestController_UpdateFlightSchedule_ReturnFlightSchedule() throws Exception {
        when(flightScheduleService.save(ArgumentMatchers.any(PutFlightScheduleDto.class))).thenReturn(flightSchedule);

        ResultActions response = mockMvc.perform(put("/api/private/flight_schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putFlightScheduleDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduleId", CoreMatchers.is(flightSchedule.getScheduleId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduledDepartureTime", CoreMatchers.is(flightSchedule.getScheduledDepartureTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.scheduledArrivalTime", CoreMatchers.is(flightSchedule.getScheduledArrivalTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(flightSchedule.getStatus())));
    }

    @Test
    public void FlightScheduleRestController_DeleteFlightScheduleById_ReturnString() throws Exception {
        int flightScheduleId = 1;
        doNothing().when(flightScheduleService).deleteById(flightScheduleId);

        ResultActions response = mockMvc.perform(delete("/api/private/flight_schedules/{id}", flightScheduleId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted flight schedule id - " + flightScheduleId));
    }

    @Test
    public void FlightScheduleRestController_DeleteAllFlightSchedules_ReturnString() throws Exception {
        when(flightScheduleService.deleteAll()).thenReturn("All flight schedules have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/flight_schedules")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All flight schedules have been deleted"));
    }
}
