package com.internationalairport.airportmanagementsystem.rest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PassengerRestController.class)
@ExtendWith(MockitoExtension.class)
public class PassengerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTGenerator jwtGenerator;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private UserEntityRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    private PostLoginDto postLoginDto;
    private PostPassengerDto postPassengerDto;
    private PutPassengerDto putPassengerDto;
    private Passenger passenger;
    private UserEntity userEntity;
    private Role role;

    @BeforeEach
    void setUp() {
        postLoginDto = new PostLoginDto();
        setField(postLoginDto, "username", "user1");
        setField(postLoginDto, "password", "password");

        userEntity = new UserEntity();
        userEntity.setUsername("user1");
        userEntity.setPassword("password");

        passenger = new Passenger("John Doe", "A1234567", "US", "123456789");
        passenger.setPassengerId(1);
        passenger.setUserEntity(userEntity);

        role = new Role();
        role.setRoleName("PASSENGER");

        postPassengerDto = new PostPassengerDto("John Doe", "A1234567", "US", "123456789", "user1", "password");
        putPassengerDto = new PutPassengerDto(1, "John Doe", "A1234567", "US", "123456789", "user1", "password");
    }

    @Test
    void testLogin() throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken(getField(postLoginDto, "username"), getField(postLoginDto, "password"));
        given(authenticationManager.authenticate(ArgumentMatchers.any(UsernamePasswordAuthenticationToken.class)))
                .willReturn(authentication);
        given(jwtGenerator.generateToken(authentication)).willReturn("token");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/public/auth/passengers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postLoginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", CoreMatchers.is("token")));
    }

    @Test
    void testRegister() throws Exception {
        given(userRepository.existsByUsername(postPassengerDto.username())).willReturn(false);
        given(roleRepository.findByRoleName("PASSENGER")).willReturn(Optional.of(role));
        given(passengerRepository.save(ArgumentMatchers.any(Passenger.class))).willReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/public/auth/passengers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postPassengerDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Passenger registered successfully!"));
    }

    @Test
    void testFindAll() throws Exception {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        given(passengerService.findAll()).willReturn(passengers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/private/passengers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(passenger.getName())));
    }

    @Test
    void testGetPassengerById() throws Exception {
        given(passengerService.findById(1)).willReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/private/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(passenger.getName())));
    }

    @Test
    void testAddPassenger() throws Exception {
        given(passengerService.save(ArgumentMatchers.any(PostPassengerDto.class))).willReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/private/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postPassengerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(passenger.getName())));
    }

    @Test
    void testUpdatePassenger() throws Exception {
        given(passengerService.save(ArgumentMatchers.any(PutPassengerDto.class))).willReturn(passenger);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/private/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putPassengerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(passenger.getName())));
    }

    @Test
    void testDeletePassenger() throws Exception {
        given(passengerService.findById(1)).willReturn(passenger);
        doNothing().when(passengerService).deleteById(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/private/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted passenger id - 1"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setField(Object targetObject, String fieldName, Object value) {
        try {
            Field field = targetObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(targetObject, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getField(Object targetObject, String fieldName) {
        try {
            Field field = targetObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(targetObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
