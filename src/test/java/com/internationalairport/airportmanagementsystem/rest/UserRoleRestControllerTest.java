package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostUserRoleDto;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRoleRestController.class)
public class UserRoleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRoleService userRoleService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserRoleRestController(userRoleService)).build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddUserRole() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);

        PostUserRoleDto postUserRoleDto = new PostUserRoleDto(1, 1);

        Mockito.when(userRoleService.save(any(PostUserRoleDto.class))).thenReturn(userEntity);

        mockMvc.perform(post("/api/private/user_roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 1, \"roleId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteUserRoleById() throws Exception {
        Mockito.doNothing().when(userRoleService).deleteByUserIdAndRoleId(anyInt(), anyInt());

        mockMvc.perform(delete("/api/private/user_roles/1/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted user role with id - 1-1"));
    }
}
