package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutEmployeeDto(
        Integer employeeId,
        String name,
        String role,
        String contactInfo
) {
}
