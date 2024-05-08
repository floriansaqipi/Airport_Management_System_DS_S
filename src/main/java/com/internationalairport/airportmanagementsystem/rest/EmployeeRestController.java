package com.internationalairport.airportmanagementsystem.rest;


import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody PostEmployeeDto postEmployeeDto) {
        return employeeService.save(postEmployeeDto);
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody PutEmployeeDto putEmployeeDto) {
        return  employeeService.save(putEmployeeDto);
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}














