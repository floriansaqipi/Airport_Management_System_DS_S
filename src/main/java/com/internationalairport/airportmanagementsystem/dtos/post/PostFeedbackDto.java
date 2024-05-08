package com.internationalairport.airportmanagementsystem.dtos.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public record PostFeedbackDto(
       String content,
       String status,
       Integer passengerId,
       Integer flightId
) {
}
