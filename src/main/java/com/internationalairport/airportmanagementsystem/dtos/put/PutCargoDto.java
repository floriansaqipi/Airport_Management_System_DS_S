package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutCargoDto(
        Integer cargoId,
        Integer flightId,
        Double weight,
        String dimensions
) {
}
