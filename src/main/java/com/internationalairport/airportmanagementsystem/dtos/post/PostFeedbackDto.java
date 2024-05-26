package com.internationalairport.airportmanagementsystem.dtos.post;

public record PostFeedbackDto(
       String content,
       String status,
       Integer passengerId,
       Integer flightId
) {
}
