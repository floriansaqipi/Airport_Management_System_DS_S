package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public Role postToRole(PostRoleDto postRoleDto) {
        Role role = new Role(
                postRoleDto.roleName()
        );
        role.setRoleId(0);


        return role;
    }

    public Role putToRole(PutRoleDto putRoleDto) {
        Role role = new Role(
                putRoleDto.roleName()
        );
        role.setRoleId(putRoleDto.roleId());


        return role;
    }
}

