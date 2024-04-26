package com.internationalairport.airportmanagementsystem.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PostFlightDto(
        String flightNumber,
        Integer departureAirportId,
        Integer arrivalAirportId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Integer aircraftId,
        List<Integer> checkIns,
        List<Integer> baggages,
        List<Integer> feedbacks,
        Integer gateAssignmentId,
        List<Integer> tickets,
        List<Integer> flightSchedules,
        List<Integer> cargos,
        List<Integer> flightCrews
) {
}
