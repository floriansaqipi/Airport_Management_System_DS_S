package com.internationalairport.airportmanagementsystem.dtos.put;

import com.internationalairport.airportmanagementsystem.entities.UserEntity;

public record PutPassengerDto(
        Integer passengerId,
        String name,
        String passportNumber,
        String nationality,
        String contactDetails,
        String username,
        String password
) {
}
