package com.internationalairport.airportmanagementsystem.dtos.post;
public record PostPassengerDto(
        String name,
        String passportNumber,
        String nationality,
        String contactDetails
) {
}
