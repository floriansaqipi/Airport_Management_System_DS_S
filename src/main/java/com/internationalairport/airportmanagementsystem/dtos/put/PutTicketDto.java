package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutTicketDto (
        Integer ticketId,
        Integer flightId,
        Integer passengerId,
        String seatNumber,
        String _class,
        Double price

) {
}
