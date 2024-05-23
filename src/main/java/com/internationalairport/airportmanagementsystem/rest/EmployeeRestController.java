package com.internationalairport.airportmanagementsystem.rest;


import com.internationalairport.airportmanagementsystem.daos.EmployeeRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.mappers.EmployeeMapper;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
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
public class EmployeeRestController {
    private EmployeeService employeeService;
    private AuthenticationManager authenticationManager;

    private JWTGenerator jwtGenerator;

    private UserEntityService userEntityService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService,
                                    AuthenticationManager authenticationManager,
                                    JWTGenerator jwtGenerator,
                                    UserEntityService userEntityService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator=jwtGenerator;
        employeeService = theEmployeeService;
        this.userEntityService = userEntityService;
    }

    @PostMapping("/public/auth/employees/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PostLoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/private/auth/employees/register")
    public ResponseEntity<String> register(@RequestBody PostEmployeeDto postEmployeeDto) {
        if (userEntityService.existsByUsername(postEmployeeDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        employeeService.save(postEmployeeDto);

        return new ResponseEntity<>("Employee registered successfully!", HttpStatus.OK);
    }
    @GetMapping("/private/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @GetMapping("/private/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    @PutMapping("/private/employees")
    public ResponseEntity<String> updateEmployee(@RequestBody PutEmployeeDto putEmployeeDto) {
        UserEntity user = userEntityService.findByUsername(putEmployeeDto.username());
        if (user == null) {
            throw new RuntimeException("User not found for username - " + putEmployeeDto.username());
        }
        if (userEntityService.existsByUsername(putEmployeeDto.username()) &&
                !putEmployeeDto.username().equals(user.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        employeeService.save(putEmployeeDto);

        return new ResponseEntity<>("Employee updated successfully!", HttpStatus.OK);
    }
    @DeleteMapping("/private/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}