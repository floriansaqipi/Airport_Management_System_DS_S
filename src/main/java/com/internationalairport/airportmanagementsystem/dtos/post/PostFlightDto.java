package com.internationalairport.airportmanagementsystem.dtos.post;

import java.time.LocalDateTime;
import java.util.List;

public record PostFlightDto(
        String flightNumber,
        Integer departureAirportId,
        Integer arrivalAirportId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Integer aircraftId,
        List<Integer>  employeeIds
) {
}
