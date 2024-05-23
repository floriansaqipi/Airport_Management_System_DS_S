package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAirlineDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirlineDto;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirlineService;
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

@WebMvcTest(controllers = AirlineRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AirlineRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    @Autowired
    private ObjectMapper objectMapper;

    private Airline airline;
    private PostAirlineDto postAirlineDto;
    private PutAirlineDto putAirlineDto;

    @BeforeEach
    public void init() {
        airline = new Airline("AA", "American Airlines");
        airline.setAirlineId(1);
        postAirlineDto = new PostAirlineDto("AA", "American Airlines");
        putAirlineDto = new PutAirlineDto(1, "AA", "American Airlines");
    }

    @Test
    public void AirlineRestController_CreateAirline_ReturnCreated() throws Exception {
        given(airlineService.save(ArgumentMatchers.any(PostAirlineDto.class))).willReturn(airline);

        ResultActions response = mockMvc.perform(post("/api/private/airlines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postAirlineDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.airlineId", CoreMatchers.is(airline.getAirlineId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(airline.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airline.getName())));
    }

    @Test
    public void AirlineRestController_GetAllAirlines_ReturnAirlineList() throws Exception {
        when(airlineService.findAll()).thenReturn(Arrays.asList(airline));

        ResultActions response = mockMvc.perform(get("/api/public/airlines")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airlineId", CoreMatchers.is(airline.getAirlineId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", CoreMatchers.is(airline.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(airline.getName())));
    }

    @Test
    public void AirlineRestController_GetAirlineById_ReturnAirline() throws Exception {
        int airlineId = 1;
        when(airlineService.findById(airlineId)).thenReturn(airline);

        ResultActions response = mockMvc.perform(get("/api/public/airlines/{id}", airlineId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.airlineId", CoreMatchers.is(airline.getAirlineId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(airline.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airline.getName())));
    }

    @Test
    public void AirlineRestController_UpdateAirline_ReturnAirline() throws Exception {
        when(airlineService.save(ArgumentMatchers.any(PutAirlineDto.class))).thenReturn(airline);

        ResultActions response = mockMvc.perform(put("/api/private/airlines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putAirlineDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.airlineId", CoreMatchers.is(airline.getAirlineId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(airline.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airline.getName())));
    }

    @Test
    public void AirlineRestController_DeleteAirlineById_ReturnString() throws Exception {
        int airlineId = 1;
        doNothing().when(airlineService).deleteById(airlineId);

        ResultActions response = mockMvc.perform(delete("/api/private/airlines/{id}", airlineId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted airline with id - " + airlineId));
    }

    @Test
    public void AirlineRestController_DeleteAllAirlines_ReturnString() throws Exception {
        when(airlineService.deleteAll()).thenReturn("All airlines have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/airlines")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All airlines have been deleted"));
    }
}
