package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutBaggageDto(
        Integer baggageId,
        Integer passengerId,
        Integer flightId,
        Double weight
) {
}
