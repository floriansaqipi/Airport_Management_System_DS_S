package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.AuthResponseDTO;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostLoginDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
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
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @Operation(
            description = "Endpoint to log in an employee",
            summary = "Login for employees",
            responses = {
                    @ApiResponse(
                            description = "Successful login",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
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

    @Operation(
            description = "Endpoint to register a new employee",
            summary = "Register new employee",
            responses = {
                    @ApiResponse(
                            description = "Successful registration",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username is taken",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/private/auth/employees/register")
    public ResponseEntity<String> register(@RequestBody PostEmployeeDto postEmployeeDto) {
        if (userEntityService.existsByUsername(postEmployeeDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        employeeService.save(postEmployeeDto);

        return new ResponseEntity<>("Employee registered successfully!", HttpStatus.OK);
    }
    @Operation(
            description = "Endpoint to retrieve all employees",
            summary = "Retrieve all employees",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/employees")
    public List<Employee> findAll() {
        UserEntity user = getAuthenticatedUser();
        if (isEmployee(user)) {
            Employee employee = employeeService.findById(user.getEmployee().getEmployeeId());
            return Collections.singletonList(employee);
        }

        return employeeService.findAll();
    }
    @Operation(
            description = "Endpoint to retrieve an employee by ID",
            summary = "Retrieve an employee by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + theEmployee);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theEmployee);

        return theEmployee;
    }

    @Operation(
            description = "Endpoint to update an employee",
            summary = "Update an employee",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/employees")
    public ResponseEntity<String> updateEmployee(@RequestBody PutEmployeeDto putEmployeeDto) {

        Employee theEmployee = employeeService.findById(putEmployeeDto.employeeId());
        UserEntity user = theEmployee.getUserEntity();

        if (userEntityService.existsByUsername(putEmployeeDto.username()) &&
                !putEmployeeDto.username().equals(user.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user1 = getAuthenticatedUser();
        authorizeAccess(user1, theEmployee);
        employeeService.save(putEmployeeDto);
        return new ResponseEntity<>("Employee updated successfully!", HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete an employee by ID",
            summary = "Delete an employee by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + theEmployee);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theEmployee);
        employeeService.deleteById(employeeId);
        return "Deleted Employee id - " + employeeId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isEmployee(UserEntity user) {
        return user.getRole().getRoleName().equals("EMPLOYEE");
    }

    private void authorizeAccess(UserEntity user, Employee employee) {
        if (isEmployee(user)) {
            Employee theEmployee = user.getEmployee();
            if (theEmployee.getEmployeeId() != employee.getEmployeeId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
    String var = "This is a commit";
}