package com.internationalairport.airportmanagementsystem.dtos.put;

import java.time.LocalDateTime;
import java.util.Date;

public record PutFlightScheduleDto(
        Integer scheduleId,
        Integer flightId,
        LocalDateTime scheduledDepartureTime,
        LocalDateTime scheduledArrivalTime,
        String status
) {
}
