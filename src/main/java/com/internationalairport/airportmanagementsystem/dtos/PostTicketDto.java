package com.internationalairport.airportmanagementsystem.dtos;

public record PostTicketDto(
        Integer flightId,
        Integer passengerId,
        String seatNumber,
        String _class,
        Double price

) {
}
