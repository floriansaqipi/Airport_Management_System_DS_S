package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employee postToEmployee(PostEmployeeDto postEmployeeDto) {
        Employee employee = new Employee(
                postEmployeeDto.name(),
                postEmployeeDto.role(),
                postEmployeeDto.contactInfo()
        );
        employee.setEmployeeId(0);
        return employee;
    }

    public Employee putToEmployee(PutEmployeeDto putEmployeeDto) {
        Employee employee = new Employee(
                putEmployeeDto.name(),
                putEmployeeDto.role(),
                putEmployeeDto.contactInfo()
        );
        employee.setEmployeeId(putEmployeeDto.employeeId());
        return employee;
    }
}
