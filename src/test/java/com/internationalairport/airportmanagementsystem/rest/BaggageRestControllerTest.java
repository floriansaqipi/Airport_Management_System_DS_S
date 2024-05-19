package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostBaggageDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBaggageDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.service.interfaces.BaggageService;
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

@WebMvcTest(controllers = BaggageRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BaggageRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BaggageService baggageService;

    @Autowired
    private ObjectMapper objectMapper;

    private Baggage baggage;
    private PostBaggageDto postBaggageDto;
    private PutBaggageDto putBaggageDto;

    @BeforeEach
    public void init() {
        baggage = new Baggage(50.0);
        baggage.setBaggageId(1);
        postBaggageDto = new PostBaggageDto(1, 1, 50.0);
        putBaggageDto = new PutBaggageDto(1, 1, 1, 50.0);
    }

    @Test
    public void BaggageRestController_CreateBaggage_ReturnCreated() throws Exception {
        given(baggageService.save(ArgumentMatchers.any(PostBaggageDto.class))).willReturn(baggage);

        ResultActions response = mockMvc.perform(post("/api/private/baggage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postBaggageDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.baggageId", CoreMatchers.is(baggage.getBaggageId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", CoreMatchers.is(baggage.getWeight())));
    }

    @Test
    public void BaggageRestController_GetAllBaggage_ReturnBaggageList() throws Exception {
        when(baggageService.findAll()).thenReturn(Arrays.asList(baggage));

        ResultActions response = mockMvc.perform(get("/api/private/baggage")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].baggageId", CoreMatchers.is(baggage.getBaggageId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", CoreMatchers.is(baggage.getWeight())));
    }

    @Test
    public void BaggageRestController_GetBaggageById_ReturnBaggage() throws Exception {
        int baggageId = 1;
        when(baggageService.findById(baggageId)).thenReturn(baggage);

        ResultActions response = mockMvc.perform(get("/api/private/baggage/{baggageId}", baggageId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.baggageId", CoreMatchers.is(baggage.getBaggageId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", CoreMatchers.is(baggage.getWeight())));
    }

    @Test
    public void BaggageRestController_UpdateBaggage_ReturnBaggage() throws Exception {
        given(baggageService.save(ArgumentMatchers.any(PutBaggageDto.class))).willReturn(baggage);

        ResultActions response = mockMvc.perform(put("/api/private/baggage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putBaggageDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.baggageId", CoreMatchers.is(baggage.getBaggageId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", CoreMatchers.is(baggage.getWeight())));
    }

    @Test
    public void BaggageRestController_DeleteBaggageById_ReturnString() throws Exception {
        int baggageId = 1;
        when(baggageService.findById(baggageId)).thenReturn(baggage);
        doNothing().when(baggageService).deleteById(baggageId);

        ResultActions response = mockMvc.perform(delete("/api/private/baggage/{baggageId}", baggageId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Baggage id - " + baggageId));
    }
}
