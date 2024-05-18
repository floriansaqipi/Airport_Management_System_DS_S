package com.internationalairport.airportmanagementsystem.dtos.post;

import java.time.LocalDateTime;
import java.util.Date;

public record PostFlightScheduleDto(
        Integer flightId,
        LocalDateTime scheduledDepartureTime,
        LocalDateTime scheduledArrivalTime,
        String status
) {
}
