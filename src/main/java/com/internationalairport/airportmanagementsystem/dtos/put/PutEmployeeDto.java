package com.internationalairport.airportmanagementsystem.dtos.put;

import com.internationalairport.airportmanagementsystem.entities.UserEntity;

public record PutEmployeeDto(
        Integer employeeId,
        String name,
        String role,
        String contactInfo,
        UserEntity userEntity
) {
}
