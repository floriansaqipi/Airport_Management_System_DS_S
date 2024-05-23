package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import com.internationalairport.airportmanagementsystem.service.interfaces.MaintenanceService;
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

@WebMvcTest(MaintenanceRestController.class)
public class MaintenanceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaintenanceService maintenanceService;

    @InjectMocks
    private MaintenanceRestController maintenanceRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAll() throws Exception {
        Maintenance maintenance1 = new Maintenance(LocalDateTime.of(2023, 5, 22, 10, 0), "Routine check-up", "Check engines and systems");
        Maintenance maintenance2 = new Maintenance(LocalDateTime.of(2023, 5, 23, 12, 0), "Engine maintenance", "Inspect and repair engine");

        given(maintenanceService.findAll()).willReturn(Arrays.asList(maintenance1, maintenance2));

        mockMvc.perform(get("/api/private/maintenances")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Routine check-up"))
                .andExpect(jsonPath("$[1].type").value("Engine maintenance"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetMaintenance() throws Exception {
        Maintenance maintenance = new Maintenance(LocalDateTime.of(2023, 5, 22, 10, 0), "Routine check-up", "Check engines and systems");

        given(maintenanceService.findById(1)).willReturn(maintenance);

        mockMvc.perform(get("/api/private/maintenances/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Routine check-up"))
                .andExpect(jsonPath("$.date").value("2023-05-22T10:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddMaintenance() throws Exception {
        PostMaintenanceDto postMaintenanceDto = new PostMaintenanceDto(LocalDateTime.of(2023, 5, 22, 10, 0), "Routine check-up", "Check engines and systems", 1);
        Maintenance maintenance = new Maintenance(LocalDateTime.of(2023, 5, 22, 10, 0), "Routine check-up", "Check engines and systems");

        given(maintenanceService.save(any(PostMaintenanceDto.class))).willReturn(maintenance);

        mockMvc.perform(post("/api/private/maintenances")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2023-05-22T10:00:00\",\"type\":\"Routine check-up\",\"description\":\"Check engines and systems\",\"aircraftId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Routine check-up"))
                .andExpect(jsonPath("$.date").value("2023-05-22T10:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateMaintenance() throws Exception {
        PutMaintenanceDto putMaintenanceDto = new PutMaintenanceDto(1, LocalDateTime.of(2023, 5, 22, 11, 0), "Updated check-up", "Updated description", 1);
        Maintenance maintenance = new Maintenance(LocalDateTime.of(2023, 5, 22, 11, 0), "Updated check-up", "Updated description");

        given(maintenanceService.save(any(PutMaintenanceDto.class))).willReturn(maintenance);

        mockMvc.perform(put("/api/private/maintenances")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"maintenanceId\":1,\"date\":\"2023-05-22T11:00:00\",\"type\":\"Updated check-up\",\"description\":\"Updated description\",\"aircraftId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Updated check-up"))
                .andExpect(jsonPath("$.date").value("2023-05-22T11:00:00"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteMaintenance() throws Exception {
        Maintenance maintenance = new Maintenance(LocalDateTime.of(2023, 5, 22, 10, 0), "Routine check-up", "Check engines and systems");

        given(maintenanceService.findById(1)).willReturn(maintenance);
        doNothing().when(maintenanceService).deleteById(1);

        mockMvc.perform(delete("/api/private/maintenances/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted maintenance id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetMaintenanceNotFound() throws Exception {
        given(maintenanceService.findById(1)).willThrow(new RuntimeException("Maintenance id not found - 1"));

        mockMvc.perform(get("/api/private/maintenances/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Maintenance id not found - 1", result.getResolvedException().getMessage()));
    }
}
