package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostCheckInDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCheckInDto;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.service.interfaces.CheckInService;
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

@WebMvcTest(controllers = CheckInRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CheckInRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInService checkInService;

    @Autowired
    private ObjectMapper objectMapper;

    private CheckIn checkIn;
    private PostCheckInDto postCheckInDto;
    private PutCheckInDto putCheckInDto;

    @BeforeEach
    public void init() {
        checkIn = new CheckIn();
        checkIn.setCheckInId(1);
        checkIn.setCheckInTime(LocalDateTime.now());
        checkIn.setDeskNumber(1);

        postCheckInDto = new PostCheckInDto(1, 1, LocalDateTime.now(), 1);
        putCheckInDto = new PutCheckInDto(1, 1, 1, LocalDateTime.now(), 1);
    }

    @Test
    public void CheckInRestController_CreateCheckIn_ReturnCreated() throws Exception {
        given(checkInService.save(ArgumentMatchers.any(PostCheckInDto.class))).willReturn(checkIn);

        ResultActions response = mockMvc.perform(post("/api/private/check_ins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postCheckInDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.checkInId", CoreMatchers.is(checkIn.getCheckInId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.checkInTime", CoreMatchers.is(checkIn.getCheckInTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deskNumber", CoreMatchers.is(checkIn.getDeskNumber())));
    }

    @Test
    public void CheckInRestController_GetAllCheckIns_ReturnCheckInList() throws Exception {
        List<CheckIn> checkInList = Arrays.asList(checkIn);
        when(checkInService.findAll()).thenReturn(checkInList);

        ResultActions response = mockMvc.perform(get("/api/private/check_ins")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkInId", CoreMatchers.is(checkIn.getCheckInId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkInTime", CoreMatchers.is(checkIn.getCheckInTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].deskNumber", CoreMatchers.is(checkIn.getDeskNumber())));
    }

    @Test
    public void CheckInRestController_GetCheckInById_ReturnCheckIn() throws Exception {
        int checkInId = 1;
        when(checkInService.findById(checkInId)).thenReturn(checkIn);

        ResultActions response = mockMvc.perform(get("/api/private/check_ins/{checkInId}", checkInId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.checkInId", CoreMatchers.is(checkIn.getCheckInId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.checkInTime", CoreMatchers.is(checkIn.getCheckInTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deskNumber", CoreMatchers.is(checkIn.getDeskNumber())));
    }

    @Test
    public void CheckInRestController_UpdateCheckIn_ReturnCheckIn() throws Exception {
        given(checkInService.save(ArgumentMatchers.any(PutCheckInDto.class))).willReturn(checkIn);

        ResultActions response = mockMvc.perform(put("/api/private/check_ins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putCheckInDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.checkInId", CoreMatchers.is(checkIn.getCheckInId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.checkInTime", CoreMatchers.is(checkIn.getCheckInTime().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deskNumber", CoreMatchers.is(checkIn.getDeskNumber())));
    }

    @Test
    public void CheckInRestController_DeleteCheckInById_ReturnString() throws Exception {
        int checkInId = 1;
        when(checkInService.findById(checkInId)).thenReturn(checkIn);
        doNothing().when(checkInService).deleteById(checkInId);

        ResultActions response = mockMvc.perform(delete("/api/private/check_ins/{checkInId}", checkInId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Check In id - " + checkInId));
    }

    @Test
    public void CheckInRestController_DeleteAllCheckIns_ReturnString() throws Exception {
        when(checkInService.deleteAll()).thenReturn("All check-ins have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/check_ins")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All check-ins have been deleted"));
    }
}