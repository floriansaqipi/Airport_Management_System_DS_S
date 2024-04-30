package com.internationalairport.airportmanagementsystem.dtos.post;

import java.time.LocalDateTime;

public record PostBoardingPassDto(
    String gate,
    LocalDateTime boardingTime,
    Integer ticketId
) {
}
