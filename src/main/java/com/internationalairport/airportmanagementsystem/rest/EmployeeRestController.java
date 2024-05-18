package com.internationalairport.airportmanagementsystem.rest;


import com.internationalairport.airportmanagementsystem.daos.EmployeeRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRegisterDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.EmployeeMapper;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
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
    private UserEntityRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;
    private JWTGenerator jwtGenerator;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService,
    AuthenticationManager authenticationManager, UserEntityRepository userRepository,
    RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator,
                                  EmployeeRepository employeeRepository,EmployeeMapper employeeMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator=jwtGenerator;
        this.employeeMapper=employeeMapper;
        this.employeeRepository=employeeRepository;
        employeeService = theEmployeeService;
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
        if (userRepository.existsByUsername(postEmployeeDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeMapper.postToEmployee(postEmployeeDto);
        String hashedPassword=passwordEncoder.encode(employee.getUserEntity().getPassword());
        employee.getUserEntity().setPassword(hashedPassword);

        Role passengerRole = roleRepository.findByRoleName("PASSENGER").get();
        Role employeeRole = roleRepository.findByRoleName("EMPLOYEE").get();
        employee.getUserEntity().addRole(passengerRole);
        employee.getUserEntity().addRole(employeeRole);

        employeeRepository.save(employee);

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
    @PostMapping("/private/employees")
    public Employee addEmployee(@RequestBody PostEmployeeDto postEmployeeDto) {
        return employeeService.save(postEmployeeDto);
    }
    @PutMapping("/private/employees")
    public Employee updateEmployee(@RequestBody PutEmployeeDto putEmployeeDto) {
        return  employeeService.save(putEmployeeDto);
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














