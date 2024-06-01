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

    @Operation(
            description = "Endpoint to get all passengers",
            summary = "Retrieve all passengers",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all passengers",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/passengers")
    public List<Passenger> findAll() {
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = passengerService.findById(user.getPassenger().getPassengerId());
            return Collections.singletonList(passenger);
        }
        return passengerService.findAll();
    }

    @Operation(
            description = "Endpoint to get a passenger by ID",
            summary = "Retrieve a passenger by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Passenger ID not found or unauthorized access",
                            responseCode = "404"
                    )
            }
    )
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

    @Operation(
            description = "Endpoint to log in a passenger",
            summary = "Login a passenger",
            responses = {
                    @ApiResponse(
                            description = "Successfully logged in",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid credentials",
                            responseCode = "400"
                    )
            }
    )
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

    @Operation(
            description = "Endpoint to register a new passenger",
            summary = "Register a new passenger",
            responses = {
                    @ApiResponse(
                            description = "Successfully registered the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username already taken",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/public/auth/passengers/register")
    public ResponseEntity<String> register(@RequestBody PostPassengerDto postPassengerDto) {
        if (userRepository.existsByUsername(postPassengerDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        passengerService.save(postPassengerDto);

        return new ResponseEntity<>("Passenger registered successfully!", HttpStatus.OK);
    }


    @Operation(
            description = "Endpoint to update a passenger",
            summary = "Update a passenger",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized access or invalid request",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/passengers")
    public ResponseEntity<String> updatePassenger(@RequestBody PutPassengerDto putPassengerDto) {

        Passenger thePassenger = passengerService.findById(putPassengerDto.passengerId());
        UserEntity user = thePassenger.getUserEntity();

        if (userEntityService.existsByUsername(putPassengerDto.username()) &&
                !putPassengerDto.username().equals(user.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user1 = getAuthenticatedUser();
        authorizeAccess(user1, thePassenger);
        passengerService.save(putPassengerDto);

        return new ResponseEntity<>("Passenger updated successfully!", HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a passenger by ID",
            summary = "Delete a passenger by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized access or invalid request",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Passenger ID not found",
                            responseCode = "404"
                    )
            }
    )
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
