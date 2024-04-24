package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {
    public Ticket toTicket(PostTicketDto postTicketDto){
        Ticket ticket = new Ticket(
                postTicketDto.seatNumber(),
                postTicketDto._class(),
                postTicketDto.price()
        );
        Passenger passenger = new Passenger();
        passenger.setId(postTicketDto.passengerId());
        Flight flight = new Flight();
        flight.setFlightId(postTicketDto.flightId());

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);
        return ticket;
    }
}
