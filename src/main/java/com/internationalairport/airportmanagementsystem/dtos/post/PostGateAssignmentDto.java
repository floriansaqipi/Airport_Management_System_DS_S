package com.internationalairport.airportmanagementsystem.dtos.post;

import java.util.Date;

public record PostGateAssignmentDto(
        String gate,
        Date assignmentTime,
        Integer flightId
) {
}
