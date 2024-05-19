package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.service.interfaces.AircraftService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AircraftRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AircraftRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @Autowired
    private ObjectMapper objectMapper;

    private Aircraft aircraft;
    private PostAircraftDto postAircraftDto;
    private PutAircraftDto putAircraftDto;

    @BeforeEach
    public void init() {
        aircraft = new Aircraft("tailNumber", "model", 200);
        postAircraftDto = new PostAircraftDto("tailNumber", "model", 200, 1);
        putAircraftDto = new PutAircraftDto(1, "tailNumber", "model", 200, 1);
    }

    @Test
    public void AircraftRestController_CreateAircraft_ReturnCreated() throws Exception {
        given(aircraftService.save(ArgumentMatchers.any(PostAircraftDto.class))).willReturn(aircraft);

        ResultActions response = mockMvc.perform(post("/api/private/aircrafts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postAircraftDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tailNumber", CoreMatchers.is(aircraft.getTailNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", CoreMatchers.is(aircraft.getModel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity", CoreMatchers.is(aircraft.getCapacity())));
    }

    @Test
    public void AircraftRestController_GetAllAircrafts_ReturnAircraftList() throws Exception {
        when(aircraftService.findAll()).thenReturn(Arrays.asList(aircraft));

        ResultActions response = mockMvc.perform(get("/api/private/aircrafts")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tailNumber", CoreMatchers.is(aircraft.getTailNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", CoreMatchers.is(aircraft.getModel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].capacity", CoreMatchers.is(aircraft.getCapacity())));
    }

    @Test
    public void AircraftRestController_GetAircraftById_ReturnAircraft() throws Exception {
        int aircraftId = 1;
        when(aircraftService.findById(aircraftId)).thenReturn(aircraft);

        ResultActions response = mockMvc.perform(get("/api/private/aircrafts/{aircraftId}", aircraftId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tailNumber", CoreMatchers.is(aircraft.getTailNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", CoreMatchers.is(aircraft.getModel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity", CoreMatchers.is(aircraft.getCapacity())));
    }

    @Test
    public void AircraftRestController_UpdateAircraft_ReturnAircraft() throws Exception {
        when(aircraftService.save(ArgumentMatchers.any(PutAircraftDto.class))).thenReturn(aircraft);

        ResultActions response = mockMvc.perform(put("/api/private/aircrafts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putAircraftDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tailNumber", CoreMatchers.is(aircraft.getTailNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", CoreMatchers.is(aircraft.getModel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity", CoreMatchers.is(aircraft.getCapacity())));
    }

    @Test
    public void AircraftRestController_DeleteAircraftById_ReturnString() throws Exception {
        int aircraftId = 1;
        when(aircraftService.findById(aircraftId)).thenReturn(aircraft);
        doNothing().when(aircraftService).deleteById(aircraftId);

        ResultActions response = mockMvc.perform(delete("/api/private/aircrafts/{aircraftId}", aircraftId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted aircraft with id - " + aircraftId));
    }

    @Test
    public void AircraftRestController_DeleteAllAircrafts_ReturnString() throws Exception {
        when(aircraftService.deleteAll()).thenReturn("All aircrafts have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/aircrafts")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All aircrafts have been deleted"));
    }
}