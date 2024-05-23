package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportService;
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

@WebMvcTest(controllers = AirportRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AirportRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Autowired
    private ObjectMapper objectMapper;

    private Airport airport;
    private PostAirportDto postAirportDto;
    private PutAirportDto putAirportDto;

    @BeforeEach
    public void init() {
        airport = new Airport();
        airport.setAirportId(1);
        airport.setCode("JFK");
        airport.setName("John F. Kennedy International Airport");
        airport.setLocationCity("New York");
        airport.setLocationCountry("USA");

        postAirportDto = new PostAirportDto("JFK", "John F. Kennedy International Airport", "New York", "USA");
        putAirportDto = new PutAirportDto(1, "JFK", "John F. Kennedy International Airport", "New York", "USA");
    }

    @Test
    public void AirportRestController_CreateAirport_ReturnCreated() throws Exception {
        given(airportService.save(ArgumentMatchers.any(PostAirportDto.class))).willReturn(airport);

        ResultActions response = mockMvc.perform(post("/api/private/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postAirportDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.airportId", CoreMatchers.is(airport.getAirportId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(airport.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airport.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationCity", CoreMatchers.is(airport.getLocationCity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationCountry", CoreMatchers.is(airport.getLocationCountry())));
    }

    @Test
    public void AirportRestController_GetAllAirports_ReturnAirportList() throws Exception {
        when(airportService.findAll()).thenReturn(Arrays.asList(airport));

        ResultActions response = mockMvc.perform(get("/api/public/airports")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airportId", CoreMatchers.is(airport.getAirportId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", CoreMatchers.is(airport.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(airport.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].locationCity", CoreMatchers.is(airport.getLocationCity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].locationCountry", CoreMatchers.is(airport.getLocationCountry())));
    }

    @Test
    public void AirportRestController_GetAirportById_ReturnAirport() throws Exception {
        int airportId = 1;
        when(airportService.findById(airportId)).thenReturn(airport);

        ResultActions response = mockMvc.perform(get("/api/public/airports/{airportId}", airportId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.airportId", CoreMatchers.is(airport.getAirportId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(airport.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airport.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationCity", CoreMatchers.is(airport.getLocationCity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationCountry", CoreMatchers.is(airport.getLocationCountry())));
    }

    @Test
    public void AirportRestController_UpdateAirport_ReturnAirport() throws Exception {
        when(airportService.save(ArgumentMatchers.any(PutAirportDto.class))).thenReturn(airport);

        ResultActions response = mockMvc.perform(put("/api/private/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putAirportDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.airportId", CoreMatchers.is(airport.getAirportId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(airport.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airport.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationCity", CoreMatchers.is(airport.getLocationCity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locationCountry", CoreMatchers.is(airport.getLocationCountry())));
    }

    @Test
    public void AirportRestController_DeleteAirportById_ReturnString() throws Exception {
        int airportId = 1;
        when(airportService.findById(airportId)).thenReturn(airport);
        doNothing().when(airportService).deleteById(airportId);

        ResultActions response = mockMvc.perform(delete("/api/private/airports/{airportId}", airportId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted airport with id - " + airportId));
    }

    @Test
    public void AirportRestController_DeleteAllAirports_ReturnString() throws Exception {
        when(airportService.deleteAll()).thenReturn("All airports have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/airports")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All airports have been deleted"));
    }
}
