package com.internationalairport.airportmanagementsystem.dtos.put;

import java.util.Date;

public record PutCheckInDto(
        Integer checkInId,
        Integer passengerId,
        Integer flightId,
        Date checkInTime,
        Integer deskNumber
) {
}
