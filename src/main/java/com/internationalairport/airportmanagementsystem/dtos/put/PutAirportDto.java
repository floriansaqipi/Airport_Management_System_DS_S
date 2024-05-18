package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutAirportDto(
        Integer airportId,
        String code,
        String name,
        String locationCity,
        String locationCountry
) {
}
