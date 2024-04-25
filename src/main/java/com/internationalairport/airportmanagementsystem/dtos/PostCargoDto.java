package com.internationalairport.airportmanagementsystem.dtos;

public record PostCargoDto(
        Integer flightId,
        Double weight,
        String dimensions
) {
}
