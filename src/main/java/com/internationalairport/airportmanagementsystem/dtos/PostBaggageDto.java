package com.internationalairport.airportmanagementsystem.dtos;

public record PostBaggageDto(
        Integer passengerId,
        Integer flightId,
        Double weight
) {
}
