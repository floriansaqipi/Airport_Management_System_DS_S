package com.internationalairport.airportmanagementsystem.dtos.post;

import java.time.LocalDateTime;

public record PostMaintenanceDto(
        LocalDateTime date,
        String type,
        String description,
        Integer aircraftId
) {
}
