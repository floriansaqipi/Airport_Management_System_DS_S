package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
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

@WebMvcTest(controllers = GateAssignmentRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class GateAssignmentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GateAssignmentService gateAssignmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private GateAssignment gateAssignment;
    private PostGateAssignmentDto postGateAssignmentDto;
    private PutGateAssignmentDto putGateAssignmentDto;

    @BeforeEach
    public void init() {
        gateAssignment = new GateAssignment("A1", LocalDateTime.of(2024, 5, 19, 10, 0));
        gateAssignment.setAssignmentId(1);

        postGateAssignmentDto = new PostGateAssignmentDto("A1", LocalDateTime.of(2024, 5, 19, 10, 0), 1);
        putGateAssignmentDto = new PutGateAssignmentDto(1, "A1", LocalDateTime.of(2024, 5, 19, 10, 0), 1);
    }

    @Test
    public void GateAssignmentRestController_CreateGateAssignment_ReturnOk() throws Exception {
        given(gateAssignmentService.save(ArgumentMatchers.any(PostGateAssignmentDto.class))).willReturn(gateAssignment);

        ResultActions response = mockMvc.perform(post("/api/private/gate_assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postGateAssignmentDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentId", CoreMatchers.is(gateAssignment.getAssignmentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gate", CoreMatchers.is(gateAssignment.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentTime", CoreMatchers.is("2024-05-19T10:00:00")));
    }

    @Test
    public void GateAssignmentRestController_GetAllGateAssignments_ReturnGateAssignmentList() throws Exception {
        when(gateAssignmentService.findAll()).thenReturn(Arrays.asList(gateAssignment));

        ResultActions response = mockMvc.perform(get("/api/public/gate_assignments")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].assignmentId", CoreMatchers.is(gateAssignment.getAssignmentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gate", CoreMatchers.is(gateAssignment.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].assignmentTime", CoreMatchers.is("2024-05-19T10:00:00")));
    }

    @Test
    public void GateAssignmentRestController_GetGateAssignmentById_ReturnGateAssignment() throws Exception {
        int gateAssignmentId = 1;
        when(gateAssignmentService.findById(gateAssignmentId)).thenReturn(gateAssignment);

        ResultActions response = mockMvc.perform(get("/api/public/gate_assignments/{id}", gateAssignmentId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentId", CoreMatchers.is(gateAssignment.getAssignmentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gate", CoreMatchers.is(gateAssignment.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentTime", CoreMatchers.is("2024-05-19T10:00:00")));
    }

    @Test
    public void GateAssignmentRestController_UpdateGateAssignment_ReturnGateAssignment() throws Exception {
        when(gateAssignmentService.save(ArgumentMatchers.any(PutGateAssignmentDto.class))).thenReturn(gateAssignment);

        ResultActions response = mockMvc.perform(put("/api/private/gate_assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putGateAssignmentDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentId", CoreMatchers.is(gateAssignment.getAssignmentId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gate", CoreMatchers.is(gateAssignment.getGate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentTime", CoreMatchers.is("2024-05-19T10:00:00")));
    }

    @Test
    public void GateAssignmentRestController_DeleteGateAssignmentById_ReturnString() throws Exception {
        int gateAssignmentId = 1;
        doNothing().when(gateAssignmentService).deleteById(gateAssignmentId);

        ResultActions response = mockMvc.perform(delete("/api/private/gate_assignments/{id}", gateAssignmentId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted gate assignment id - " + gateAssignmentId));
    }

    @Test
    public void GateAssignmentRestController_DeleteAllGateAssignments_ReturnString() throws Exception {
        when(gateAssignmentService.deleteAll()).thenReturn("All gate assignments have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/gate_assignments")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All gate assignments have been deleted"));
    }
}
