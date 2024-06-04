package com.internationalairport.airportmanagementsystem.service.implementations;


import com.internationalairport.airportmanagementsystem.daos.EmployeeRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.EmployeeMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    private RoleRepository roleRepository;

    private UserEntityService userEntityService;
    private UserEntityRepository userEntityRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository,
                               EmployeeMapper thEmployeeMapper,
                               RoleRepository roleRepository,
                               UserEntityService userEntityService) {
        employeeRepository = theEmployeeRepository;
        employeeMapper = thEmployeeMapper;
        this.roleRepository = roleRepository;
        this.userEntityService = userEntityService;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(PutEmployeeDto putEmployeeDto) {
        Employee employee = employeeMapper.putToEmployee(putEmployeeDto);
        UserEntity user = findById(employee.getEmployeeId()).getUserEntity();
        Role role = user.getRole();
        employee.getUserEntity().setUserId(user.getUserId());
        employee.getUserEntity().setRole(role);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee save(PostEmployeeDto postEmployeeDto) {
        Employee employee = employeeMapper.postToEmployee(postEmployeeDto);
        Role employeeRole = roleRepository.findByRoleName("EMPLOYEE").get();
        employee.getUserEntity().setRole(employeeRole);
        return employeeRepository.save(employee);
    }


    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}






