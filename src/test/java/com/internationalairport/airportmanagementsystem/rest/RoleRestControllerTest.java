package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleService;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoleRestController.class)
public class RoleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RoleRestController(roleService)).build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testFindAllRoles() throws Exception {
        Role role = new Role("ADMIN");
        role.setRoleId(1);
        List<Role> roles = Collections.singletonList(role);

        Mockito.when(roleService.findAll()).thenReturn(roles);

        mockMvc.perform(get("/api/private/roles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetRoleById() throws Exception {
        Role role = new Role("ADMIN");
        role.setRoleId(1);

        Mockito.when(roleService.findById(anyInt())).thenReturn(role);

        mockMvc.perform(get("/api/private/roles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddRole() throws Exception {
        Role role = new Role("ADMIN");
        role.setRoleId(1);

        PostRoleDto postRoleDto = new PostRoleDto("ADMIN", null);

        Mockito.when(roleService.save(any(PostRoleDto.class))).thenReturn(role);

        mockMvc.perform(post("/api/private/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"roleName\": \"ADMIN\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateRole() throws Exception {
        Role role = new Role("ADMIN");
        role.setRoleId(1);

        PutRoleDto putRoleDto = new PutRoleDto(1, "ADMIN", null);

        Mockito.when(roleService.save(any(PutRoleDto.class))).thenReturn(role);

        mockMvc.perform(put("/api/private/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"roleId\": 1, \"roleName\": \"ADMIN\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roleName").value("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteRoleById() throws Exception {
        Role role = new Role("ADMIN");
        role.setRoleId(1);

        Mockito.when(roleService.findById(anyInt())).thenReturn(role);
        Mockito.doNothing().when(roleService).deleteById(anyInt());

        mockMvc.perform(delete("/api/private/roles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Role with id - 1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteAllRoles() throws Exception {
        Mockito.when(roleService.deleteAll()).thenReturn("1 Roles have been deleted");

        mockMvc.perform(delete("/api/private/roles"))
                .andExpect(status().isOk())
                .andExpect(content().string("1 Roles have been deleted"));
    }
}
