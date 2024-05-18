package com.internationalairport.airportmanagementsystem.dtos.put;

import java.time.LocalDateTime;
import java.util.Date;

public record PutCheckInDto(
        Integer checkInId,
        Integer passengerId,
        Integer flightId,
        LocalDateTime checkInTime,
        Integer deskNumber
) {
}
