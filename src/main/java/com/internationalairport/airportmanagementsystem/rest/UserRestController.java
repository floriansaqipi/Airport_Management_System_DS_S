package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserEntityService userEntityService;

    @Autowired
    public UserRestController(UserEntityService UserEntityService) {
        this.userEntityService = UserEntityService;
    }

    @GetMapping("/users")
    public List<UserEntity> findAllUsers() {
        return userEntityService.findAll();
    }

    @GetMapping("/users/{userId}")
    public UserEntity getUserById(@PathVariable Integer userId) {
        UserEntity user = userEntityService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for id - " + userId);
        }
        return user;
    }

    @PostMapping("/users")
    public UserEntity addUser(@RequestBody PostUserDto postUserDto) {
        return userEntityService.save(postUserDto);
    }

    @PutMapping("/users")
    public UserEntity updateUser(@RequestBody PutUserDto putUserDto) {
        return userEntityService.save(putUserDto);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable Integer userId) {
        UserEntity user = userEntityService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for id - " + userId);
        }
        userEntityService.deleteById(userId);
        return "Deleted user with id - " + userId;
    }

    @DeleteMapping("/users")
    public String deleteAllUsers() {
        return userEntityService.deleteAll();
    }
}
