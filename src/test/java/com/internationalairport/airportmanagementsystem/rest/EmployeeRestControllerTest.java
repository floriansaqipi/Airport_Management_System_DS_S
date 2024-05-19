package com.internationalairport.airportmanagementsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internationalairport.airportmanagementsystem.dtos.post.PostEmployeeDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutEmployeeDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private EmployeeRestController employeeRestController;


    @Test
    public void testRegisterEmployee() throws Exception {
        PostEmployeeDto postEmployeeDto = new PostEmployeeDto("John Doe", "Manager", "1234567890", "johndoe", "password");

        when(employeeService.save(any(PostEmployeeDto.class))).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/private/auth/employees/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLoginEmployee() throws Exception {
        PostEmployeeDto postEmployeeDto = new PostEmployeeDto("John Doe", "Manager", "1234567890", "johndoe", "password");

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
        PostEmployeeDto postEmployeeDto = new PostEmployeeDto("John Doe", "Manager", "1234567890", "johndoe", "password");

        when(employeeService.save(any(PostEmployeeDto.class))).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/private/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        PutEmployeeDto putEmployeeDto = new PutEmployeeDto(1, "John Doe", "Manager", "1234567890", new UserEntity());

        when(employeeService.save(any(PutEmployeeDto.class))).thenReturn(new Employee());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/private/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putEmployeeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        int employeeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/private/employees/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}