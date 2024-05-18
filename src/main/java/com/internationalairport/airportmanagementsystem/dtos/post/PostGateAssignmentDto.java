package com.internationalairport.airportmanagementsystem.dtos.post;

import java.time.LocalDateTime;
import java.util.Date;

public record PostGateAssignmentDto(
        String gate,
        LocalDateTime assignmentTime,
        Integer flightId
) {
}
