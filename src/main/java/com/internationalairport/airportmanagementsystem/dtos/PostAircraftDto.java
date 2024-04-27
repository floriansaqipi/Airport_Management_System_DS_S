package com.internationalairport.airportmanagementsystem.dtos;

import java.util.List;

public record PostAircraftDto(
        String tailNumber,
        String model,
        Integer capacity,
        Integer airlineId,
        List<Integer> flights,
        List<Integer> maintenances
) {
}
