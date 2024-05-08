package com.internationalairport.airportmanagementsystem.dtos.post;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record PostEmployeeDto(
        String name,
        String role,
        String contactInfo
) {
}
