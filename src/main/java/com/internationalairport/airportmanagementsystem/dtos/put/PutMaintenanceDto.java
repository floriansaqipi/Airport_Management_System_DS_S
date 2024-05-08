package com.internationalairport.airportmanagementsystem.dtos.put;

import java.time.LocalDateTime;

public record PutMaintenanceDto(
        Integer maintenanceId,
        LocalDateTime date,
        String type,
        String description,
        Integer aircraftId
) {
}
