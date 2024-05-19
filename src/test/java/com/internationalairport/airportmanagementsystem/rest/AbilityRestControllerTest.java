package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.service.interfaces.AbilityService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AbilityRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AbilityRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbilityService abilityService;

    @Autowired
    private ObjectMapper objectMapper;

    private Ability ability;
    private PostAbilityDto postAbilityDto;
    private PutAbilityDto putAbilityDto;

    @BeforeEach
    public void init() {
        ability = new Ability("entity", "verb", "field");
        postAbilityDto = new PostAbilityDto("entity", "verb", "field");
        putAbilityDto = new PutAbilityDto(1, "entity", "verb", "field");
    }

    @Test
    public void AbilityRestController_CreateAbility_ReturnCreated() throws Exception {
        given(abilityService.save(ArgumentMatchers.any(PostAbilityDto.class))).willReturn(ability);

        ResultActions response = mockMvc.perform(post("/api/private/abilities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postAbilityDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.entity", CoreMatchers.is(ability.getEntity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.verb", CoreMatchers.is(ability.getVerb())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.field", CoreMatchers.is(ability.getField())));
    }

    @Test
    public void AbilityRestController_GetAllAbilities_ReturnAbilityList() throws Exception {
        when(abilityService.findAll()).thenReturn(Arrays.asList(ability));

        ResultActions response = mockMvc.perform(get("/api/private/abilities")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].entity", CoreMatchers.is(ability.getEntity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].verb", CoreMatchers.is(ability.getVerb())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field", CoreMatchers.is(ability.getField())));
    }

    @Test
    public void AbilityRestController_GetAbilityById_ReturnAbility() throws Exception {
        int abilityId = 1;
        when(abilityService.findById(abilityId)).thenReturn(ability);

        ResultActions response = mockMvc.perform(get("/api/private/abilities/{abilityId}", abilityId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.entity", CoreMatchers.is(ability.getEntity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.verb", CoreMatchers.is(ability.getVerb())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.field", CoreMatchers.is(ability.getField())));
    }

    @Test
    public void AbilityRestController_UpdateAbility_ReturnAbility() throws Exception {
        when(abilityService.save(ArgumentMatchers.any(PutAbilityDto.class))).thenReturn(ability);

        ResultActions response = mockMvc.perform(put("/api/private/abilities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putAbilityDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.entity", CoreMatchers.is(ability.getEntity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.verb", CoreMatchers.is(ability.getVerb())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.field", CoreMatchers.is(ability.getField())));
    }

    @Test
    public void AbilityRestController_DeleteAbilityById_ReturnString() throws Exception {
        int abilityId = 1;
        when(abilityService.findById(abilityId)).thenReturn(ability);
        doNothing().when(abilityService).deleteById(abilityId);

        ResultActions response = mockMvc.perform(delete("/api/private/abilities/{abilityId}", abilityId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted ability with id - " + abilityId));
    }

    @Test
    public void AbilityRestController_DeleteAllAbilities_ReturnString() throws Exception {
        when(abilityService.deleteAll()).thenReturn("All abilities have been deleted");

        ResultActions response = mockMvc.perform(delete("/api/private/abilities")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("All abilities have been deleted"));
    }
}
