package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRegisterDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.mappers.PassengerMapper;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
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

import java.util.Collections;
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

    private UserEntityService userEntityService;
    @Autowired
    public PassengerRestController(PassengerService thePassengerService, AuthenticationManager authenticationManager, UserEntityRepository userRepository,
                                   RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator,
                                   PassengerMapper passengerMapper, PassengerRepository passengerRepository, UserEntityService userEntityService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator=jwtGenerator;
        this.passengerMapper=passengerMapper;
        passengerService = thePassengerService;
        this.passengerRepository = passengerRepository;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/private/passengers")
    public List<Passenger> findAll() {
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = passengerService.findById(user.getPassenger().getPassengerId());
            return Collections.singletonList(passenger);
        }
        return passengerService.findAll();
    }

    @GetMapping("/private/passengers/{passengerId}")
    public Passenger getPassenger(@PathVariable int passengerId) {
        Passenger thePassenger = passengerService.findById(passengerId);
        if (thePassenger == null) {
            throw new RuntimeException("Passenger id not found - " + passengerId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, thePassenger);
        return thePassenger;
    }

    @PostMapping("/public/auth/passengers/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PostLoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/public/auth/passengers/register")
    public ResponseEntity<String> register(@RequestBody PostPassengerDto postPassengerDto) {
        if (userRepository.existsByUsername(postPassengerDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        passengerService.save(postPassengerDto);

        return new ResponseEntity<>("Passenger registered successfully!", HttpStatus.OK);
    }
    @PutMapping("/private/passengers")
    public ResponseEntity<String> updatePassenger(@RequestBody PutPassengerDto putPassengerDto) {
        UserEntity user = userEntityService.findByUsername(putPassengerDto.username());
        Passenger thePassenger = passengerService.findById(putPassengerDto.passengerId());
        if (user == null) {
            throw new RuntimeException("User not found for username - " + putPassengerDto.username());
        }
        if (userEntityService.existsByUsername(putPassengerDto.username()) &&
                !putPassengerDto.username().equals(user.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        if(thePassenger == null){
            throw new RuntimeException("Passenger id not found - " + thePassenger);
        }
        UserEntity user1 = getAuthenticatedUser();
        authorizeAccess(user1, thePassenger);

        passengerService.save(putPassengerDto);

        return new ResponseEntity<>("Passenger updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/private/passengers/{passengerId}")
    public String deletePassenger(@PathVariable int passengerId) {
        Passenger tempPassenger = passengerService.findById(passengerId);
        if (tempPassenger == null) {
            throw new RuntimeException("Passenger id not found - " + passengerId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, tempPassenger);
        passengerService.deleteById(passengerId);
        return "Deleted passenger id - " + passengerId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }

    private void authorizeAccess(UserEntity user, Passenger thepassenger) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != thepassenger.getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }

}
