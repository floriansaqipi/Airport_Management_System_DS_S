package com.internationalairport.airportmanagementsystem.dtos;

import java.time.LocalDateTime;

public record PostBoardingPassDto(
    Integer tickedId,
    String gate,
    LocalDateTime boardingTime
) {
}
