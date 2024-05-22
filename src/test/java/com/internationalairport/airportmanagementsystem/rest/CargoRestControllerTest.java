package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostCargoDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.service.interfaces.CargoService;
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

@WebMvcTest(controllers = CargoRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CargoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargoService cargoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cargo cargo;
    private PostCargoDto postCargoDto;
    private PutCargoDto putCargoDto;

    @BeforeEach
    public void init() {
        cargo = new Cargo(100.0, "10x10x10");
        cargo.setCargoId(1); // Ensure the cargoId is set
        postCargoDto = new PostCargoDto(1, 100.0, "10x10x10");
        putCargoDto = new PutCargoDto(1, 1, 100.0, "10x10x10");
    }

    @Test
    public void CargoRestController_CreateCargo_ReturnCreated() throws Exception {
        given(cargoService.save(ArgumentMatchers.any(PostCargoDto.class))).willReturn(cargo);

        ResultActions response = mockMvc.perform(post("/api/private/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postCargoDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", CoreMatchers.is(cargo.getWeight())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions", CoreMatchers.is(cargo.getDimensions())));
    }

    @Test
    public void CargoRestController_GetAllCargos_ReturnCargoList() throws Exception {
        when(cargoService.findAll()).thenReturn(Arrays.asList(cargo));

        ResultActions response = mockMvc.perform(get("/api/private/cargo")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", CoreMatchers.is(cargo.getWeight())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dimensions", CoreMatchers.is(cargo.getDimensions())));
    }

    @Test
    public void CargoRestController_GetCargoById_ReturnCargo() throws Exception {
        int cargoId = 1;
        when(cargoService.findById(cargoId)).thenReturn(cargo);

        ResultActions response = mockMvc.perform(get("/api/private/cargo/{cargoId}", cargoId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", CoreMatchers.is(cargo.getWeight())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions", CoreMatchers.is(cargo.getDimensions())));
    }

    @Test
    public void CargoRestController_UpdateCargo_ReturnCargo() throws Exception {
        when(cargoService.save(ArgumentMatchers.any(PutCargoDto.class))).thenReturn(cargo);

        ResultActions response = mockMvc.perform(put("/api/private/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putCargoDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", CoreMatchers.is(cargo.getWeight())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dimensions", CoreMatchers.is(cargo.getDimensions())));
    }

    @Test
    public void CargoRestController_DeleteCargoById_ReturnString() throws Exception {
        int cargoId = 1;
        when(cargoService.findById(cargoId)).thenReturn(cargo);
        doNothing().when(cargoService).deleteById(cargoId);

        ResultActions response = mockMvc.perform(delete("/api/private/cargo/{cargoId}", cargoId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Cargo id - " + cargoId));
    }
}