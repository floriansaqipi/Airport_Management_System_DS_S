package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Role;

import java.util.List;

public interface AbilityService {
    Ability save(PostAbilityDto postAbilityDto);

    Ability save(PutAbilityDto putAbilityDto);

    Ability findById(Integer abilityId);
    List<Ability> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
