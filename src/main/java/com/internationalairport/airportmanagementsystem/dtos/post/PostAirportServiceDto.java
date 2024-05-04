package com.internationalairport.airportmanagementsystem.dtos.post;


public record PostAirportServiceDto(
        String name,
        String location,
        String operatingHours
) {
}