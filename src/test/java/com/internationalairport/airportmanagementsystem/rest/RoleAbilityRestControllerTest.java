package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleAbilityDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleAbilityService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoleAbilityRestController.class)
public class RoleAbilityRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleAbilityService roleAbilityService;

    @InjectMocks
    private RoleAbilityRestController roleAbilityRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddRoleAbility() throws Exception {
        PostRoleAbilityDto postRoleAbilityDto = new PostRoleAbilityDto(1, 1);
        Role role = new Role();
        role.setRoleId(1);
        Ability ability = new Ability();
        ability.setAbilityId(1);
        role.addAbility(ability);

        given(roleAbilityService.save(any(PostRoleAbilityDto.class))).willReturn(role);

        mockMvc.perform(post("/api/private/role_abilities")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roleId\":1,\"abilityId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleId").value(1))
                .andExpect(jsonPath("$.abilities[0].abilityId").value(1));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteRoleAbilityById() throws Exception {
        doNothing().when(roleAbilityService).deleteByRoleIdAndAbilityId(eq(1), eq(1));

        mockMvc.perform(delete("/api/private/role_abilities/1/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted role ability with id - 1-1"));
    }
}

