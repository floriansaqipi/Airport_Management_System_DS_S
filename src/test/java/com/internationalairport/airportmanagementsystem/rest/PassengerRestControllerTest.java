package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.PassengerMapper;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PassengerRestController.class)
public class PassengerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserEntityService userEntityService;

    @MockBean
    private JWTGenerator jwtGenerator;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Mock
    private Authentication authentication;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PassengerMapper passengerMapper;

    @InjectMocks
    private PassengerRestController passengerRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setUserId(1);
        mockUserEntity.setUsername("johndoe");

        given(userEntityRepository.findByUsername(any(String.class))).willReturn(Optional.of(mockUserEntity));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAllPassengers() throws Exception {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setName("John Doe");
        given(passengerService.findAll()).willReturn(Collections.singletonList(passenger));

        mockMvc.perform(get("/api/private/passengers")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].passengerId").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetPassengerById() throws Exception {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setName("John Doe");
        given(passengerService.findById(1)).willReturn(passenger);

        mockMvc.perform(get("/api/private/passengers/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passengerId").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddPassenger() throws Exception {
        PostPassengerDto postPassengerDto = new PostPassengerDto("John Doe", "12345", "USA", "john.doe@example.com", "johndoe", "password");
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setName("John Doe");

        given(passengerService.save(any(PostPassengerDto.class))).willReturn(passenger);

        mockMvc.perform(post("/api/public/auth/passengers/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"passportNumber\":\"12345\",\"nationality\":\"USA\",\"contactDetails\":\"john.doe@example.com\",\"username\":\"johndoe\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Passenger registered successfully!"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdatePassenger() throws Exception {
        PutPassengerDto putPassengerDto = new PutPassengerDto(1, "John Doe Updated", "12345", "USA", "john.doe@example.com", "johndoe", "newpassword");
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setName("John Doe Updated");

        given(passengerService.save(any(PutPassengerDto.class))).willReturn(passenger);

        mockMvc.perform(put("/api/private/passengers")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"passengerId\":1,\"name\":\"John Doe Updated\",\"passportNumber\":\"12345\",\"nationality\":\"USA\",\"contactDetails\":\"john.doe@example.com\",\"username\":\"johndoe\",\"password\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passengerId").value(1))
                .andExpect(jsonPath("$.name").value("John Doe Updated"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeletePassengerById() throws Exception {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        given(passengerService.findById(1)).willReturn(passenger);
        doNothing().when(passengerService).deleteById(1);

        mockMvc.perform(delete("/api/private/passengers/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted passenger id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testLoginPassenger() throws Exception {
        PostLoginDto loginDto = new PostLoginDto();
        loginDto.setUsername("johndoe");
        loginDto.setPassword("password");

        given(authenticationManager.authenticate(any())).willReturn(authentication);
        given(jwtGenerator.generateToken(any())).willReturn("token");

        mockMvc.perform(post("/api/public/auth/passengers/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"johndoe\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token"));
    }
}
