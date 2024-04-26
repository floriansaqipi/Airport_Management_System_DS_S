package com.internationalairport.airportmanagementsystem.dtos;

public record PostAircraftDto(
        String tailNumber,
        String model,
        Integer capacity,
        Integer airlineId
) {
}
