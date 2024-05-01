package com.internationalairport.airportmanagementsystem.dtos;

import java.util.List;

public record PostAirportDto(
        String code,
        String name,
        String locationCity,
        String locationCountry,
        List<Integer> departures,
        List<Integer> arrivals,
        List<Integer> employees
) {
}
