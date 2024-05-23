package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    public UserEntity postToUser(PostUserDto postUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(postUserDto.username());
        user.setPassword(passwordEncoder.encode((postUserDto.password())));
        user.setUserId(0);

        return user;
    }

    public UserEntity putToUser(PutUserDto putUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(putUserDto.username());
        user.setPassword(passwordEncoder.encode((putUserDto.password())));
        user.setUserId(putUserDto.userId());

        if(putUserDto.roleId() != null){
            Role role = new Role();
            role.setRoleId(putUserDto.roleId());
            user.setRole(role);
        }

        return user;
    }
}
