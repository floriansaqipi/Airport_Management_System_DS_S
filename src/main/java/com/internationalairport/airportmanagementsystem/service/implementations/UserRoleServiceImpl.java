package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FlightRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private UserEntityRepository userEntityRepository;
    private UserEntityService userEntityService;
    private RoleService roleService;

    @Autowired
    public UserRoleServiceImpl(UserEntityRepository userEntityRepository, UserEntityService userEntityService, RoleService roleService) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityService = userEntityService;
        this.roleService = roleService;
    }

    @Override
    public UserEntity save(PostUserRoleDto postUserRoleDto) {
        UserEntity userEntity = userEntityService.findById(postUserRoleDto.userId());
        Role role = roleService.findById(postUserRoleDto.roleId());
        userEntity.addRole(role);
        return userEntityRepository.save(userEntity);
    }

    @Override
    public void deleteByUserIdAndRoleId(Integer userId, Integer roleId) {
        UserEntity userEntity = userEntityService.findById(userId);
        Role role = roleService.findById(roleId);
        userEntity.getRoles().remove(role);
        userEntityRepository.save(userEntity);
    }
}
