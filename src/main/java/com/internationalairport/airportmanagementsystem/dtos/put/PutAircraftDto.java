package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutAircraftDto (
        Integer aircraftId,
        String tailNumber,
        String model,
        Integer capacity,
        Integer airlineId
) {
}
