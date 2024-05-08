package com.internationalairport.airportmanagementsystem.dtos.put;

import java.time.LocalDateTime;
import java.util.List;

public record PutFlightDto(
        Integer flightId,
        String flightNumber,
        Integer departureAirportId,
        Integer arrivalAirportId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Integer aircraftId,
        List<Integer> employeeIds
) {
}
