package com.internationalairport.airportmanagementsystem.dtos.post;

public record PostBaggageDto(
        Integer passengerId,
        Integer flightId,
        Double weight
) {
}
