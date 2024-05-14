package com.internationalairport.airportmanagementsystem.dtos.post;

import com.internationalairport.airportmanagementsystem.entities.UserEntity;

public record PostPassengerDto(
        String name,
        String passportNumber,
        String nationality,
        String contactDetails,
        UserEntity userEntity
) {
}
