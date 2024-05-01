package com.internationalairport.airportmanagementsystem.dtos.post;

import java.util.Date;

public record PostFlightScheduleDto(
        Integer flightId,
        Date scheduledDepartureTime,
        Date scheduledArrivalTime,
        String status
) {
}
