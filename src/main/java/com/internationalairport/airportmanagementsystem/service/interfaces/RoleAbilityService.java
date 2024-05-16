package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;

public interface RoleAbilityService {
    Role save(PostRoleAbilityDto postRoleAbilityDto);
    void deleteByRoleIdAndAbilityId(Integer roleId, Integer abilityId);
}
