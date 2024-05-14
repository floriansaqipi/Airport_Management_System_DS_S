package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRegisterDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.PassengerMapper;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
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
public class PassengerRestController {
    private PassengerService passengerService;
    private AuthenticationManager authenticationManager;
    private UserEntityRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private PassengerMapper passengerMapper;
    private PassengerRepository passengerRepository;
    @Autowired
    public PassengerRestController(PassengerService thePassengerService, AuthenticationManager authenticationManager, UserEntityRepository userRepository,
                                   RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator,
                                   PassengerMapper passengerMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator=jwtGenerator;
        this.passengerMapper=passengerMapper;
        passengerService = thePassengerService;
    }

    @GetMapping("/passengers")
    public List<Passenger> findAll() {
        return passengerService.findAll();
    }

    @GetMapping("/passengers/{passengerId}")
    public Passenger getPassenger(@PathVariable int passengerId) {
        Passenger thePassenger = passengerService.findById(passengerId);
        if (thePassenger == null) {
            throw new RuntimeException("Passenger id not found - " + passengerId);
        }
        return thePassenger;
    }
    @PostMapping("/auth/passengers/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PostLoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/auth/passengers/register")
    public ResponseEntity<String> register(@RequestBody PostPassengerDto postPassengerDto) {
        if (userRepository.existsByUsername(postPassengerDto.userEntity().getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Passenger passenger = passengerMapper.postToPassenger(postPassengerDto);
        String hashedPassword=passwordEncoder.encode(passenger.getUserEntity().getPassword());
        passenger.getUserEntity().setPassword(hashedPassword);

        Role userRole = roleRepository.findByRoleName("USER").get();
        passenger.getUserEntity().addRole(userRole);

        passengerRepository.save(passenger);

        return new ResponseEntity<>("Passenger registered successfully!", HttpStatus.OK);
    }
    @PostMapping("/passengers")
    public Passenger addPassenger(@RequestBody PostPassengerDto postPassengerDto) {
        return passengerService.save(postPassengerDto);
    }
    @PutMapping("/passengers")
    public Passenger updatePassenger(@RequestBody PutPassengerDto putPassengerDto) {
        return passengerService.save(putPassengerDto);
    }
    @DeleteMapping("/passengers/{passengerId}")
    public String deletePassenger(@PathVariable int passengerId) {
        Passenger tempPassenger = passengerService.findById(passengerId);
        if (tempPassenger == null) {
            throw new RuntimeException("Passenger id not found - " + passengerId);
        }
        passengerService.deleteById(passengerId);
        return "Deleted passenger id - " + passengerId;
    }

}
