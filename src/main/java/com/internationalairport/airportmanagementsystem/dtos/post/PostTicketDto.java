package com.internationalairport.airportmanagementsystem.dtos.post;

public record PostTicketDto(
        Integer flightId,
        Integer passengerId,
        String seatNumber,
        String _class,
        Double price

) {
}
