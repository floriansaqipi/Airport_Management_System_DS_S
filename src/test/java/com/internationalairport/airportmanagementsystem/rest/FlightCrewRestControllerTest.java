package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightCrewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightCrewRestControllerTest {

    @Mock
    private FlightCrewService flightCrewService;

    @InjectMocks
    private FlightCrewRestController flightCrewRestController;

    @Test
    void testAddFlightCrew() throws Exception {
        PostFlightCrewDto postDto = new PostFlightCrewDto(1, 1001); // Sample data
        Flight mockFlight = new Flight(); // Mock Flight object

        when(flightCrewService.save(postDto)).thenReturn(mockFlight);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(flightCrewRestController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/private/flight_crews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"flightId\": 1, \"employeeId\": 1001 }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(flightCrewService, times(1)).save(postDto);
    }

    @Test
    void testDeleteFlightCrewById() throws Exception {
        int flightId = 1;
        int employeeId = 1001;

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(flightCrewRestController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/private/flight_crews/{flightId}/{employeeId}", flightId, employeeId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(flightCrewService, times(1)).deleteByFlightIdAndEmployeeId(flightId, employeeId);
    }

}
