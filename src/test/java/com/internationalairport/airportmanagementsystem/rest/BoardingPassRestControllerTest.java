package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostBoardingPassDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBoardingPassDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.service.interfaces.BoardingPassService;
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
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = BoardingPassRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BoardingPassRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardingPassService boardingPassService;

    @Autowired
    private ObjectMapper objectMapper;

    private BoardingPass boardingPass;
    private PostBoardingPassDto postBoardingPassDto;
    private PutBoardingPassDto putBoardingPassDto;

    @BeforeEach
    public void init() {
        boardingPass = new BoardingPass("Gate A", LocalDateTime.now());
        boardingPass.setBoardingPassId(1);
        postBoardingPassDto = new PostBoardingPassDto("Gate A", LocalDateTime.now(), 1);
        putBoardingPassDto = new PutBoardingPassDto(1, "Gate B", LocalDateTime.now().plusHours(1), 1);
    }

    @Test
    public void BoardingPassRestController_CreateBoardingPass_ReturnCreated() throws Exception {
        given(boardingPassService.save(ArgumentMatchers.any(PostBoardingPassDto.class))).willReturn(boardingPass);

        ResultActions response = mockMvc.perform(post("/api/private/boarding_passes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postBoardingPassDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingPassId", CoreMatchers.is(boardingPass.getBoardingPassId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gate", CoreMatchers.is(boardingPass.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingTime", CoreMatchers.startsWith(boardingPass.getBoardingTime().toString().substring(0, 19))));
    }

    @Test
    public void BoardingPassRestController_GetAllBoardingPasses_ReturnBoardingPassList() throws Exception {
        List<BoardingPass> boardingPassList = Arrays.asList(boardingPass);
        when(boardingPassService.findAll()).thenReturn(boardingPassList);

        ResultActions response = mockMvc.perform(get("/api/private/boarding_passes")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].boardingPassId", CoreMatchers.is(boardingPass.getBoardingPassId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gate", CoreMatchers.is(boardingPass.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].boardingTime", CoreMatchers.startsWith(boardingPass.getBoardingTime().toString().substring(0, 19))));
    }

    @Test
    public void BoardingPassRestController_GetBoardingPassById_ReturnBoardingPass() throws Exception {
        int boardingPassId = 1;
        when(boardingPassService.findById(boardingPassId)).thenReturn(boardingPass);

        ResultActions response = mockMvc.perform(get("/api/private/boarding_passes/{boarding_passId}", boardingPassId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingPassId", CoreMatchers.is(boardingPass.getBoardingPassId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gate", CoreMatchers.is(boardingPass.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingTime", CoreMatchers.startsWith(boardingPass.getBoardingTime().toString().substring(0, 19))));
    }

    @Test
    public void BoardingPassRestController_UpdateBoardingPass_ReturnBoardingPass() throws Exception {
        given(boardingPassService.save(ArgumentMatchers.any(PutBoardingPassDto.class))).willReturn(boardingPass);

        ResultActions response = mockMvc.perform(put("/api/private/boarding_passes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putBoardingPassDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingPassId", CoreMatchers.is(boardingPass.getBoardingPassId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gate", CoreMatchers.is(boardingPass.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingTime", CoreMatchers.startsWith(boardingPass.getBoardingTime().toString().substring(0, 19))));
    }


    @Test
    public void BoardingPassRestController_DeleteBoardingPassById_ReturnString() throws Exception {
        int boardingPassId = 1;
        when(boardingPassService.findById(boardingPassId)).thenReturn(boardingPass);
        doNothing().when(boardingPassService).deleteById(boardingPassId);

        ResultActions response = mockMvc.perform(delete("/api/private/boarding_passes/{boarding_passId}", boardingPassId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Boarding Pass id - " + boardingPassId));
    }
}
