package com.internationalairport.airportmanagementsystem.dtos.post;

import java.time.LocalDateTime;
import java.util.Date;

public record PostCheckInDto(
        Integer passengerId,
        Integer flightId,
        LocalDateTime checkInTime,
        Integer deskNumber
) {
}
