package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;

import java.util.List;

public interface UserEntityService {
    UserEntity save(PostUserDto postUserEntityDto);
    UserEntity save(PutUserDto putUserEntityDto);
    UserEntity findById(Integer userId);
    List<UserEntity> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
