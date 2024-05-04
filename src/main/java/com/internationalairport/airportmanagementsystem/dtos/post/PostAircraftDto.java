package com.internationalairport.airportmanagementsystem.dtos.post;

import java.util.List;

public record PostAircraftDto(
        String tailNumber,
        String model,
        Integer capacity,
        Integer airlineId
) {
}
