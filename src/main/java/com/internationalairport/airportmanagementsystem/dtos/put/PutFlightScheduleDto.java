package com.internationalairport.airportmanagementsystem.dtos.put;

import java.util.Date;

public record PutFlightScheduleDto(
        Integer scheduleId,
        Integer flightId,
        Date scheduledDepartureTime,
        Date scheduledArrivalTime,
        String status
) {
}
