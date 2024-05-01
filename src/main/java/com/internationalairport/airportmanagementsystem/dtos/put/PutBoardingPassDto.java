package com.internationalairport.airportmanagementsystem.dtos.put;

import java.time.LocalDateTime;

public record PutBoardingPassDto (

        Integer boardingPassId,
        String gate,
        LocalDateTime boardingTime,
        Integer ticketId
) {
}
