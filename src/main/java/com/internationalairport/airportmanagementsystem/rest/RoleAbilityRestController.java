package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleAbilityService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleAbilityRestController {

    private RoleAbilityService roleAbilityService;

    @Autowired
    public RoleAbilityRestController(RoleAbilityService roleAbilityService) {
        this.roleAbilityService = roleAbilityService;
    }


    @PostMapping("/role_abilities")
    public Role addRoleAbility(@RequestBody PostRoleAbilityDto postRoleAbilityDto) {
        return roleAbilityService.save(postRoleAbilityDto);
    }

    @DeleteMapping("/role_abilities/{roleId}/{abilityId}")
    public String deleteRoleAbilityById(@PathVariable int roleId, @PathVariable int abilityId) {
        roleAbilityService.deleteByRoleIdAndAbilityId(roleId,abilityId);
        return "Deleted role ability with id - " +  roleId + "-" + abilityId;
    }
}
