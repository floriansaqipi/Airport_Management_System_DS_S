package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AbilityRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.mappers.RoleMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AbilityServiceImpl implements AbilityService {
    private AbilityRepository abilityRepository;
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository theRoleRepository, RoleMapper theRoleMapper) {
        roleRepository = theRoleRepository;
        roleMapper = theRoleMapper;
    }
    @Override
    public Role save(PostRoleDto postRoleDto) {
        Role role = roleMapper.postToRole(postRoleDto);
        return roleRepository.save(role);
    }

    @Override
    public Role save(PutRoleDto putRoleDto) {
        Role role = roleMapper.putToRole(putRoleDto);
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Integer RoleId) {
        Optional<Role> result = roleRepository.findById(RoleId);
        Role theRole = null;
        if (result.isPresent()) {
            theRole = result.get();
        } else {
            throw new RuntimeException("Role with ID " + RoleId + " not found");
        }
        return theRole;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(Integer RoleId) {
        roleRepository.deleteById(RoleId);
    }

    @Override
    public String deleteAll() {
        int numberOfRoles = (int) roleRepository.count();
        roleRepository.deleteAll();
        return numberOfRoles + " Roles have been deleted";
    }
}
