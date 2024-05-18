package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public Role postToRole(PostRoleDto postRoleDto) {
        Role role = new Role(
                postRoleDto.roleName()
        );
        role.setRoleId(0);

        if(postRoleDto.abilityIds() != null && !postRoleDto.abilityIds().isEmpty()){
            for (Integer abilityId: postRoleDto.abilityIds()) {
                Ability ability = new Ability();
                ability.setAbilityId(abilityId);
                role.addAbility(ability);
            }
        }

        return role;
    }

    public Role putToRole(PutRoleDto putRoleDto) {
        Role role = new Role(
                putRoleDto.roleName()
        );
        role.setRoleId(putRoleDto.roleId());

        if(putRoleDto.abilityIds() != null && !putRoleDto.abilityIds().isEmpty()){
            for (Integer abilityId: putRoleDto.abilityIds()) {
                Ability ability = new Ability();
                ability.setAbilityId(abilityId);
                role.addAbility(ability);
            }
        }
        return role;
    }
}

