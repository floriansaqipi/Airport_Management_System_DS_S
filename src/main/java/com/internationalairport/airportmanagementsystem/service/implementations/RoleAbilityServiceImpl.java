package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleAbilityDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.AbilityService;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleAbilityService;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAbilityServiceImpl implements RoleAbilityService {

    private RoleRepository roleRepository;
    private RoleService roleService;
    private AbilityService abilityService;

    @Autowired
    public RoleAbilityServiceImpl(RoleRepository roleRepository, RoleService roleService, AbilityService abilityService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.abilityService = abilityService;
    }



    @Override
    public Role save(PostRoleAbilityDto postRoleAbilityDto) {
        Role role = roleService.findById(postRoleAbilityDto.roleId());
        Ability ability = abilityService.findById(postRoleAbilityDto.abilityId());
        role.addAbility(ability);
        return roleRepository.save(role);
    }

    @Override
    public void deleteByRoleIdAndAbilityId(Integer roleId, Integer abilityId) {
        Role role = roleService.findById(roleId);
        Ability ability = abilityService.findById(abilityId);
        role.getAbilities().remove(ability);
        roleRepository.save(role);
    }
}
