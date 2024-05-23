package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeMapper(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    public Employee postToEmployee(PostEmployeeDto postEmployeeDto) {
        Employee employee = new Employee(
                postEmployeeDto.name(),
                postEmployeeDto.role(),
                postEmployeeDto.contactInfo()
        );
        employee.setEmployeeId(0);
        UserEntity user= new UserEntity(postEmployeeDto.username(),
                passwordEncoder.encode(postEmployeeDto.password()));
        employee.setUserEntity(user);
        return employee;
    }

    public Employee putToEmployee(PutEmployeeDto putEmployeeDto) {
        Employee employee = new Employee(
                putEmployeeDto.name(),
                putEmployeeDto.role(),
                putEmployeeDto.contactInfo()
        );
        employee.setEmployeeId(putEmployeeDto.employeeId());
        UserEntity user= new UserEntity(putEmployeeDto.username(),
                passwordEncoder.encode(putEmployeeDto.password()));
        employee.setUserEntity(user);
        return employee;
    }
}
