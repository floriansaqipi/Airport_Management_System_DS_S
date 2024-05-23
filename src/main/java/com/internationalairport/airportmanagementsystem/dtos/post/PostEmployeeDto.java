package com.internationalairport.airportmanagementsystem.dtos.post;

public record PostEmployeeDto(
        String name,
        String role,
        String contactInfo,
        String username,
        String password
) {
}
