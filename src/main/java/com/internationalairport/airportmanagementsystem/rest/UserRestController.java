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
    private UserEntityRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    private UserEntityService userEntityService;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager,
                              UserEntityRepository userRepository,
                              RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder,
                              JWTGenerator jwtGenerator,
                              UserEntityService userEntityService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.userEntityService = userEntityService;
    }

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

    @PostMapping("/public/auth/users/register")
    public ResponseEntity<String> register(@RequestBody PostRegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role userRole = roleRepository.findByRoleName("PASSENGER").get();
        Role employeeRole = roleRepository.findByRoleName("EMPLOYEE").get();
        Role adminRole = roleRepository.findByRoleName("ADMIN").get();
        user.addRole(userRole);
        user.addRole(employeeRole);
        user.addRole(adminRole);

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @GetMapping("/private/users")
    public List<UserEntity> findAllUsers() {
        return userEntityService.findAll();
    }

    @GetMapping("/private/users/{userId}")
    public UserEntity getUserById(@PathVariable Integer userId) {
        UserEntity user = userEntityService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for id - " + userId);
        }
        return user;
    }

    @PostMapping("/private/users")
    public UserEntity addUser(@RequestBody PostUserDto postUserDto) {
        return userEntityService.save(postUserDto);
    }

    @PutMapping("/private/users")
    public UserEntity updateUser(@RequestBody PutUserDto putUserDto) {
        return userEntityService.save(putUserDto);
    }

    @DeleteMapping("/private/users/{userId}")
    public String deleteUserById(@PathVariable Integer userId) {
        UserEntity user = userEntityService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found for id - " + userId);
        }
        userEntityService.deleteById(userId);
        return "Deleted user with id - " + userId;
    }

    @DeleteMapping("/private/users")
    public String deleteAllUsers() {
        return userEntityService.deleteAll();
    }
}
