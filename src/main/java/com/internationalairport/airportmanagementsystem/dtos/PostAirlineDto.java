package com.internationalairport.airportmanagementsystem.dtos;

import java.util.List;

public record PostAirlineDto(
        String code,
        String name,
        List<Integer> aircrafts
) {
}
