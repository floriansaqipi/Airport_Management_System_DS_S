package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutAirlineDto(
        Integer airlineId,
        String code,
        String name
) {
}
