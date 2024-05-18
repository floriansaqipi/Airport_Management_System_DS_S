package com.internationalairport.airportmanagementsystem.dtos.post;

import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record PostEmployeeDto(
        String name,
        String role,
        String contactInfo,
        String username,
        String password
) {
}
