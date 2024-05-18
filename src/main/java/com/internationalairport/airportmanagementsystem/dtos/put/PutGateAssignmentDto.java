package com.internationalairport.airportmanagementsystem.dtos.put;

import java.time.LocalDateTime;
import java.util.Date;

public record PutGateAssignmentDto(
        Integer assignmentId,
        String gate,
        LocalDateTime assignmentTime,
        Integer flightId
) {
}
