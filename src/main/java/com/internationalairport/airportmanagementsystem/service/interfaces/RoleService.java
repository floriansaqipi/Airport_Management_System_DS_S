package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;

import java.util.List;

public interface RoleService {

    Role save(PostRoleDto postRoleDto);

    Role save(PutRoleDto putRoleDto);

    Role findById(Integer userId);
    List<Role> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
