package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutFeedbackDto(
        Integer feedbackId,
        String content,
        String status,
        Integer passengerId,
        Integer flightId
) {
}
