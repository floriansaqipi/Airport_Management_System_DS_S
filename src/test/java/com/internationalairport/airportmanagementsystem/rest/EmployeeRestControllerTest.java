package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.daos.EmployeeRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.EmployeeMapper;
import com.internationalairport.airportmanagementsystem.security.JWTGenerator;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = EmployeeRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private EmployeeRestController employeeRestController;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserEntityRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private EmployeeMapper employeeMapper;
    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    private JWTGenerator jwtGenerator;

    @InjectMocks
    private EmployeeRestController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegisterEmployeeSuccess() throws Exception {
        PostEmployeeDto postEmployeeDto = new PostEmployeeDto("John Doe", "EMPLOYEE", "1234567890", "johndoe", "password");
        Employee employee = new Employee();
        employee.setUserEntity(new UserEntity());
        String hashedPassword = "hashedPassword";
        Role passengerRole = new Role("PASSENGER");
        Role employeeRole = new Role("EMPLOYEE");

        when(userRepository.existsByUsername(postEmployeeDto.username())).thenReturn(false);
        when(employeeMapper.postToEmployee(any(PostEmployeeDto.class))).thenReturn(employee);
        when(passwordEncoder.encode(any(String.class))).thenReturn(hashedPassword);
        when(roleRepository.findByRoleName("PASSENGER")).thenReturn(Optional.of(passengerRole));
        when(roleRepository.findByRoleName("EMPLOYEE")).thenReturn(Optional.of(employeeRole));

        String expectedResponse = "Employee registered successfully!";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/private/auth/employees/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testLoginEmployee() throws Exception {
        PostEmployeeDto postEmployeeDto = new PostEmployeeDto("John Doe", "EMPLOYEE", "1234567890", "johndoe", "password");

        when(employeeService.save(any(PostEmployeeDto.class))).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/public/auth/employees/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        when(employeeService.findAll()).thenReturn(List.of(new Employee(), new Employee()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/private/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        int employeeId = 1;
        when(employeeService.findById(employeeId)).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/private/employees/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddEmployee() throws Exception {
        PostEmployeeDto postEmployeeDto = new PostEmployeeDto("John Doe", "EMPLOYEE", "1234567890", "johndoe", "password");

        when(employeeService.save(any(PostEmployeeDto.class))).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/private/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        PutEmployeeDto putEmployeeDto = new PutEmployeeDto(1,"John Doe", "EMPLOYEE", "1234567890", "johndoe", "password");

        when(employeeService.save(any(PutEmployeeDto.class))).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/private/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        int employeeId = 1;
        Employee employee = new Employee();
        when(employeeService.findById(employeeId)).thenReturn(employee);
        doNothing().when(employeeService).deleteById(employeeId);

        ResultActions response = mockMvc.perform(delete("/api/private/employees/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted employee id - " + employeeId));
    }
}