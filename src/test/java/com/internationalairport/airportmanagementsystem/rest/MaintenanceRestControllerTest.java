package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import com.internationalairport.airportmanagementsystem.service.interfaces.MaintenanceService;
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

@WebMvcTest(controllers = MaintenanceRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MaintenanceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaintenanceService maintenanceService;

    @Autowired
    private ObjectMapper objectMapper;

    private Maintenance maintenance;
    private PostMaintenanceDto postMaintenanceDto;
    private PutMaintenanceDto putMaintenanceDto;

    @BeforeEach
    public void init() {
        maintenance = new Maintenance(LocalDateTime.of(2024, 5, 19, 10, 0, 0),
                "Type1", "Description1");
        maintenance.setMaintenanceId(1);

        postMaintenanceDto = new PostMaintenanceDto(LocalDateTime.of(2024, 5, 19, 10, 0, 0),
                "Type1", "Description1", 1);

        putMaintenanceDto = new PutMaintenanceDto(1, LocalDateTime.of(2024, 5, 19, 10, 0, 0),
                "Type1", "Description1", 1);
    }

    @Test
    public void MaintenanceRestController_CreateMaintenance_ReturnCreated() throws Exception {
        given(maintenanceService.save(ArgumentMatchers.any(PostMaintenanceDto.class))).willReturn(maintenance);

        ResultActions response = mockMvc.perform(post("/api/private/maintenances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postMaintenanceDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.maintenanceId", CoreMatchers.is(maintenance.getMaintenanceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(maintenance.getDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(maintenance.getType())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(maintenance.getDescription())));
    }

    @Test
    public void MaintenanceRestController_GetAllMaintenances_ReturnMaintenanceList() throws Exception {
        when(maintenanceService.findAll()).thenReturn(Arrays.asList(maintenance));

        ResultActions response = mockMvc.perform(get("/api/private/maintenances")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].maintenanceId", CoreMatchers.is(maintenance.getMaintenanceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date", CoreMatchers.is(maintenance.getDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", CoreMatchers.is(maintenance.getType())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", CoreMatchers.is(maintenance.getDescription())));
    }

    @Test
    public void MaintenanceRestController_GetMaintenanceById_ReturnMaintenance() throws Exception {
        int maintenanceId = 1;
        when(maintenanceService.findById(maintenanceId)).thenReturn(maintenance);

        ResultActions response = mockMvc.perform(get("/api/private/maintenances/{maintenanceId}", maintenanceId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.maintenanceId", CoreMatchers.is(maintenance.getMaintenanceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(maintenance.getDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(maintenance.getType())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(maintenance.getDescription())));
    }

    @Test
    public void MaintenanceRestController_UpdateMaintenance_ReturnUpdatedMaintenance() throws Exception {
        when(maintenanceService.save(ArgumentMatchers.any(PutMaintenanceDto.class))).thenReturn(maintenance);

        ResultActions response = mockMvc.perform(put("/api/private/maintenances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putMaintenanceDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.maintenanceId", CoreMatchers.is(maintenance.getMaintenanceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", CoreMatchers.is(maintenance.getDate().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(maintenance.getType())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(maintenance.getDescription())));
    }

    @Test
    public void MaintenanceRestController_DeleteMaintenanceById_ReturnString() throws Exception {
        int maintenanceId = 1;
        doNothing().when(maintenanceService).deleteById(maintenanceId);

        ResultActions response = mockMvc.perform(delete("/api/private/maintenances/{maintenanceId}", maintenanceId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted maintenance id - " + maintenanceId));
    }
}
