package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutPassengerDto(
        Integer passengerId,
        String name,
        String passportNumber,
        String nationality,
        String contactDetails
) {
}
