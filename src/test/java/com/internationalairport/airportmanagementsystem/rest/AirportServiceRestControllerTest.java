package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportServiceDto;
import com.internationalairport.airportmanagementsystem.entities.AirportService;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportServiceService;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AirportServiceRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AirportServiceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportServiceService airportServiceService;

    @Autowired
    private ObjectMapper objectMapper;

    private AirportService airportService;
    private PostAirportServiceDto postAirportServiceDto;
    private PutAirportServiceDto putAirportServiceDto;

    @BeforeEach
    public void init() {
        airportService = new AirportService();
        airportService.setServiceId(1);
        airportService.setName("Baggage Handling");
        airportService.setLocation("Terminal 1");
        airportService.setOperatingHours("24/7");

        postAirportServiceDto = new PostAirportServiceDto("Baggage Handling", "Terminal 1", "24/7");
        putAirportServiceDto = new PutAirportServiceDto(1, "Baggage Handling", "Terminal 1", "24/7");
    }

    @Test
    public void AirportServiceRestController_CreateAirportService_ReturnCreated() throws Exception {
        given(airportServiceService.save(ArgumentMatchers.any(PostAirportServiceDto.class))).willReturn(airportService);

        ResultActions response = mockMvc.perform(post("/api/private/airport_services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postAirportServiceDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.serviceId", CoreMatchers.is(airportService.getServiceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airportService.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(airportService.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operatingHours", CoreMatchers.is(airportService.getOperatingHours())));
    }

    @Test
    public void AirportServiceRestController_GetAllAirportServices_ReturnAirportServiceList() throws Exception {
        when(airportServiceService.findAll()).thenReturn(Arrays.asList(airportService));

        ResultActions response = mockMvc.perform(get("/api/public/airport_services")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serviceId", CoreMatchers.is(airportService.getServiceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(airportService.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location", CoreMatchers.is(airportService.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].operatingHours", CoreMatchers.is(airportService.getOperatingHours())));
    }

    @Test
    public void AirportServiceRestController_GetAirportServiceById_ReturnAirportService() throws Exception {
        int serviceId = 1;
        when(airportServiceService.findById(serviceId)).thenReturn(airportService);

        ResultActions response = mockMvc.perform(get("/api/public/airport_services/{serviceId}", serviceId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.serviceId", CoreMatchers.is(airportService.getServiceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airportService.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(airportService.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operatingHours", CoreMatchers.is(airportService.getOperatingHours())));
    }

    @Test
    public void AirportServiceRestController_UpdateAirportService_ReturnUpdatedAirportService() throws Exception {
        when(airportServiceService.save(ArgumentMatchers.any(PutAirportServiceDto.class))).thenReturn(airportService);

        ResultActions response = mockMvc.perform(put("/api/private/airport_services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putAirportServiceDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.serviceId", CoreMatchers.is(airportService.getServiceId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(airportService.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", CoreMatchers.is(airportService.getLocation())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operatingHours", CoreMatchers.is(airportService.getOperatingHours())));
    }

    @Test
    public void AirportServiceRestController_DeleteAirportServiceById_ReturnString() throws Exception {
        int serviceId = 1;
        when(airportServiceService.findById(serviceId)).thenReturn(airportService);
        doNothing().when(airportServiceService).deleteById(serviceId);

        ResultActions response = mockMvc.perform(delete("/api/private/airport_services/{serviceId}", serviceId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted Airport Service id - " + serviceId));
    }

    @Test
    public void AirportServiceRestController_DeleteAllAirportServices_ReturnString() throws Exception {
        when(airportServiceService.deleteAll()).thenReturn("All airport services have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/airport_services")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All airport services have been deleted"));
    }
}
