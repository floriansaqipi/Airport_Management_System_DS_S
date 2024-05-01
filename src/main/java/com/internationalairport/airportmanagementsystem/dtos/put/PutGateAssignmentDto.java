package com.internationalairport.airportmanagementsystem.dtos.put;

import java.util.Date;

public record PutGateAssignmentDto(
        Integer assignmentId,
        String gate,
        Date assignmentTime,
        Integer flightId
) {
}
