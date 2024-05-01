package com.internationalairport.airportmanagementsystem.dtos.post;

import java.util.Date;

public record PostCheckInDto(
        Integer passengerId,
        Integer flightId,
        Date checkInTime,
        Integer deskNumber
) {
}
