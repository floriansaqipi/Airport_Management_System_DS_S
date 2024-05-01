package com.internationalairport.airportmanagementsystem.dtos.post;

public record PostCargoDto(
        Integer flightId,
        Double weight,
        String dimensions
) {
}
