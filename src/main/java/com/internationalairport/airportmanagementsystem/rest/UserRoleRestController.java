package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightCrewService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRoleRestController {

    private UserRoleService userRoleService;

    @Autowired
    public UserRoleRestController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/user_roles")
    public UserEntity addUserRole(@RequestBody PostUserRoleDto postUserRoleDto) {
        return userRoleService.save(postUserRoleDto);
    }

    @DeleteMapping("/user_roles/{userId}/{roleId}")
    public String deleteUserRoleById(@PathVariable int userId, @PathVariable int roleId) {
        userRoleService.deleteByUserIdAndRoleId(userId, roleId);
        return "Deleted user role with id - " +  userId + "-" + roleId;
    }
}
