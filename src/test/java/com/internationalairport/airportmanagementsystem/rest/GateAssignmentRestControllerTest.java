package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
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

@WebMvcTest(GateAssignmentRestController.class)
public class GateAssignmentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GateAssignmentService gateAssignmentService;

    @InjectMocks
    private GateAssignmentRestController gateAssignmentRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAll() throws Exception {
        GateAssignment gateAssignment1 = new GateAssignment("A1", LocalDateTime.of(2023, 5, 22, 10, 0));
        GateAssignment gateAssignment2 = new GateAssignment("B2", LocalDateTime.of(2023, 5, 23, 12, 0));

        given(gateAssignmentService.findAll()).willReturn(Arrays.asList(gateAssignment1, gateAssignment2));

        mockMvc.perform(get("/api/public/gate_assignments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gate").value("A1"))
                .andExpect(jsonPath("$[1].gate").value("B2"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetGateAssignmentById() throws Exception {
        GateAssignment gateAssignment = new GateAssignment("A1", LocalDateTime.of(2023, 5, 22, 10, 0));

        given(gateAssignmentService.findById(1)).willReturn(gateAssignment);

        mockMvc.perform(get("/api/public/gate_assignments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gate").value("A1"))
                .andExpect(jsonPath("$.assignmentTime").value("2023-05-22T10:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddGateAssignment() throws Exception {
        PostGateAssignmentDto postGateAssignmentDto = new PostGateAssignmentDto("A1", LocalDateTime.of(2023, 5, 22, 10, 0), 1);
        GateAssignment gateAssignment = new GateAssignment("A1", LocalDateTime.of(2023, 5, 22, 10, 0));

        given(gateAssignmentService.save(any(PostGateAssignmentDto.class))).willReturn(gateAssignment);

        mockMvc.perform(post("/api/private/gate_assignments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gate\":\"A1\",\"assignmentTime\":\"2023-05-22T10:00:00\",\"flightId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gate").value("A1"))
                .andExpect(jsonPath("$.assignmentTime").value("2023-05-22T10:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateGateAssignment() throws Exception {
        PutGateAssignmentDto putGateAssignmentDto = new PutGateAssignmentDto(1, "B2", LocalDateTime.of(2023, 5, 23, 12, 0), 1);
        GateAssignment gateAssignment = new GateAssignment("B2", LocalDateTime.of(2023, 5, 23, 12, 0));

        given(gateAssignmentService.save(any(PutGateAssignmentDto.class))).willReturn(gateAssignment);

        mockMvc.perform(put("/api/private/gate_assignments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"assignmentId\":1,\"gate\":\"B2\",\"assignmentTime\":\"2023-05-23T12:00:00\",\"flightId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gate").value("B2"))
                .andExpect(jsonPath("$.assignmentTime").value("2023-05-23T12:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteGateAssignmentById() throws Exception {
        GateAssignment gateAssignment = new GateAssignment("A1", LocalDateTime.of(2023, 5, 22, 10, 0));

        given(gateAssignmentService.findById(1)).willReturn(gateAssignment);
        doNothing().when(gateAssignmentService).deleteById(1);

        mockMvc.perform(delete("/api/private/gate_assignments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted gate assignment id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetGateAssignmentNotFound() throws Exception {
        given(gateAssignmentService.findById(1)).willThrow(new RuntimeException("Gate assignment id not found - 1"));

        mockMvc.perform(get("/api/public/gate_assignments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Gate assignment id not found - 1", result.getResolvedException().getMessage()));
    }
}
