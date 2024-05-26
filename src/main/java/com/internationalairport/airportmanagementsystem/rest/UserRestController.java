package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRegisterDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private AuthenticationManager authenticationManager;

    private JWTGenerator jwtGenerator;

    private UserEntityService userEntityService;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager,
                              JWTGenerator jwtGenerator,
                              UserEntityService userEntityService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Login to the system",
            summary = "Endpoint to login",
            responses = {
                    @ApiResponse(
                            description = "Successfully logged in and received authentication token",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid credentials",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/public/auth/users/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PostLoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @Operation(
            description = "Register a new user",
            summary = "Endpoint to register a new user",
            responses = {
                    @ApiResponse(
                            description = "Successfully registered a new user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username is already taken",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/public/auth/users/register")
    public ResponseEntity<String> register(@RequestBody PostUserDto postUserDto) {
        if (userEntityService.existsByUsername(postUserDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        userEntityService.save(postUserDto);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @Operation(
            description = "Get all users (private endpoint)",
            summary = "Get all users endpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all users",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/users")
    public List<UserEntity> findAllUsers() {
        return userEntityService.findAll();
    }

    @Operation(
            description = "Get a user by ID (private endpoint)",
            summary = "Endpoint to get user by ID ",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/users/{userId}")
    public UserEntity getUserById(@PathVariable Integer userId) {
        UserEntity user = userEntityService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for id - " + userId);
        }
        return user;
    }

    @Operation(
            description = "Update a user (private endpoint)",
            summary = "Endpoint to update user",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID not found or Username is taken",
                            responseCode = "404 or 400"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/users")
    public ResponseEntity<String> updateUser(@RequestBody PutUserDto putUserDto) {
        UserEntity user = userEntityService.findById(putUserDto.userId());
        if (user == null) {
            throw new RuntimeException("User not found for id - " + putUserDto.userId());
        }
        if (userEntityService.existsByUsername(putUserDto.username()) &&
                !putUserDto.username().equals(user.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        userEntityService.save(putUserDto);
        return new ResponseEntity<>("User updated success!", HttpStatus.OK);
    }


    @Operation(
            description = "Delete a user by ID (private endpoint)",
            summary = "Endpoint to delete user by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/users/{userId}")
    public String deleteUserById(@PathVariable Integer userId) {
        UserEntity user = userEntityService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for id - " + userId);
        }
        userEntityService.deleteById(userId);
        return "Deleted user with id - " + userId;
    }

    @Operation(
            description = "Delete all users (private endpoint)",
            summary = "Endpoint to delete all users",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all users",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/users")
    public String deleteAllUsers() {
        return userEntityService.deleteAll();
    }
}
