package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserEntityService userEntityService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTGenerator jwtGenerator;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindAllUsers() throws Exception {
        UserEntity user = new UserEntity("admin", "password");
        user.setUserId(1);

        given(userEntityService.findAll()).willReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/private/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].username").value("admin"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserById() throws Exception {
        UserEntity user = new UserEntity("admin", "password");
        user.setUserId(1);

        given(userEntityService.findById(1)).willReturn(user);

        mockMvc.perform(get("/api/private/users/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testRegisterUser() throws Exception {
        PostUserDto postUserDto = new PostUserDto("admin", "password");

        given(userEntityService.save(any(PostUserDto.class))).willReturn(new UserEntity());

        mockMvc.perform(post("/api/public/auth/users/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered success!"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateUser() throws Exception {
        PutUserDto putUserDto = new PutUserDto(1, "admin", "newpassword", 1);
        UserEntity user = new UserEntity("admin", "oldpassword");
        user.setUserId(1);

        given(userEntityService.findById(1)).willReturn(user);
        given(userEntityService.save(any(PutUserDto.class))).willReturn(user);

        mockMvc.perform(put("/api/private/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"username\":\"admin\",\"password\":\"newpassword\",\"roleId\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User updated success!"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteUserById() throws Exception {
        UserEntity user = new UserEntity("admin", "password");
        user.setUserId(1);

        given(userEntityService.findById(1)).willReturn(user);
        doNothing().when(userEntityService).deleteById(1);

        mockMvc.perform(delete("/api/private/users/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted user with id - 1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteAllUsers() throws Exception {
        given(userEntityService.deleteAll()).willReturn("1 Users have been deleted");

        mockMvc.perform(delete("/api/private/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1 Users have been deleted"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testLogin() throws Exception {
        PostLoginDto loginDto = new PostLoginDto();
        loginDto.setUsername("username");
        loginDto.setPassword("password");
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);

        given(authenticationManager.authenticate(any())).willReturn(authentication);
        given(jwtGenerator.generateToken(authentication)).willReturn("token");

        mockMvc.perform(post("/api/public/auth/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token"));
    }
}
