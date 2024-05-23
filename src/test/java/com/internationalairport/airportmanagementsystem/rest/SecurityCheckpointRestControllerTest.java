package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import com.internationalairport.airportmanagementsystem.service.interfaces.SecurityCheckpointService;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SecurityCheckpointRestController.class)
public class SecurityCheckpointRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityCheckpointService securityCheckpointService;

    @InjectMocks
    private SecurityCheckpointRestController securityCheckpointRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAllSecurityCheckpoints() throws Exception {
        SecurityCheckpoint securityCheckpoint = new SecurityCheckpoint("Main Entrance", "24/7");
        securityCheckpoint.setCheckpointId(1);
        List<SecurityCheckpoint> allSecurityCheckpoints = Collections.singletonList(securityCheckpoint);

        given(securityCheckpointService.findAll()).willReturn(allSecurityCheckpoints);

        mockMvc.perform(get("/api/private/security_checkpoints")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].checkpointId").value(1))
                .andExpect(jsonPath("$[0].location").value("Main Entrance"))
                .andExpect(jsonPath("$[0].operatingHours").value("24/7"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetSecurityCheckpointById() throws Exception {
        SecurityCheckpoint securityCheckpoint = new SecurityCheckpoint("Main Entrance", "24/7");
        securityCheckpoint.setCheckpointId(1);

        given(securityCheckpointService.findById(1)).willReturn(securityCheckpoint);

        mockMvc.perform(get("/api/private/security_checkpoints/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.checkpointId").value(1))
                .andExpect(jsonPath("$.location").value("Main Entrance"))
                .andExpect(jsonPath("$.operatingHours").value("24/7"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddSecurityCheckpoint() throws Exception {
        PostSecurityCheckpointDto postSecurityCheckpointDto = new PostSecurityCheckpointDto("Main Entrance", "24/7");
        SecurityCheckpoint securityCheckpoint = new SecurityCheckpoint("Main Entrance", "24/7");
        securityCheckpoint.setCheckpointId(1);

        given(securityCheckpointService.save(any(PostSecurityCheckpointDto.class))).willReturn(securityCheckpoint);

        mockMvc.perform(post("/api/private/security_checkpoints")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"location\":\"Main Entrance\",\"operatingHours\":\"24/7\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.checkpointId").value(1))
                .andExpect(jsonPath("$.location").value("Main Entrance"))
                .andExpect(jsonPath("$.operatingHours").value("24/7"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateSecurityCheckpoint() throws Exception {
        PutSecurityCheckpointDto putSecurityCheckpointDto = new PutSecurityCheckpointDto(1, "Main Entrance Updated", "24/7 Updated");
        SecurityCheckpoint securityCheckpoint = new SecurityCheckpoint("Main Entrance Updated", "24/7 Updated");
        securityCheckpoint.setCheckpointId(1);

        given(securityCheckpointService.save(any(PutSecurityCheckpointDto.class))).willReturn(securityCheckpoint);

        mockMvc.perform(put("/api/private/security_checkpoints")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"checkpointId\":1,\"location\":\"Main Entrance Updated\",\"operatingHours\":\"24/7 Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.checkpointId").value(1))
                .andExpect(jsonPath("$.location").value("Main Entrance Updated"))
                .andExpect(jsonPath("$.operatingHours").value("24/7 Updated"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteSecurityCheckpointById() throws Exception {
        SecurityCheckpoint securityCheckpoint = new SecurityCheckpoint("Main Entrance", "24/7");
        securityCheckpoint.setCheckpointId(1);

        given(securityCheckpointService.findById(1)).willReturn(securityCheckpoint);
        doNothing().when(securityCheckpointService).deleteById(eq(1));

        mockMvc.perform(delete("/api/private/security_checkpoints/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Security Checkpoint id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteAllSecurityCheckpoints() throws Exception {
        given(securityCheckpointService.deleteAll()).willReturn("1 rows have been deleted");

        mockMvc.perform(delete("/api/private/security_checkpoints")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1 rows have been deleted"));
    }
}
