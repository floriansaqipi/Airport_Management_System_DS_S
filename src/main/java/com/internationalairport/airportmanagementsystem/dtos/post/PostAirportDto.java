package com.internationalairport.airportmanagementsystem.dtos.post;

import java.util.List;

public record PostAirportDto(
        String code,
        String name,
        String locationCity,
        String locationCountry
) {
}
