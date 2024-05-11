package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserEntity postToUser(PostUserDto postUserDto) {
        UserEntity userEntity = new UserEntity(
                postUserDto.username(),
                postUserDto.password()
        );
        userEntity.setUserId(0);

        if(postUserDto.roleIds() != null && !postUserDto.roleIds().isEmpty()){
            for (Integer roleId: postUserDto.roleIds()) {
                Role role = new Role();
                role.setRoleId(roleId);
                userEntity.addRole(role);
            }
        }

        return userEntity;
    }

    public UserEntity putToUser(PutUserDto putUserDto) {
        UserEntity userEntity = new UserEntity(
                putUserDto.username(),
                putUserDto.password()
        );
        userEntity.setUserId(putUserDto.userId());

        if(putUserDto.roleIds() != null && !putUserDto.roleIds().isEmpty()){
            for (Integer roleId: putUserDto.roleIds()) {
                Role role = new Role();
                role.setRoleId(roleId);
                userEntity.addRole(role);
            }
        }

        return userEntity;
    }
}
