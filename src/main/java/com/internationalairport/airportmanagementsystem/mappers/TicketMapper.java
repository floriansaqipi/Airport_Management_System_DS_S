package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostTicketDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutTicketDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {
    public Ticket postToTicket(PostTicketDto postTicketDto){
        Ticket ticket = new Ticket(
                postTicketDto.seatNumber(),
                postTicketDto._class(),
                postTicketDto.price()
        );
        ticket.setTicketId(0);

        Passenger passenger = new Passenger();
        passenger.setPassengerId(postTicketDto.passengerId());
        Flight flight = new Flight();
        flight.setFlightId(postTicketDto.flightId());

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);

        return ticket;
    }

    public Ticket putToTicket(PutTicketDto putTicketDto){
        Ticket ticket = new Ticket(
                putTicketDto.seatNumber(),
                putTicketDto._class(),
                putTicketDto.price()
        );
        ticket.setTicketId(putTicketDto.ticketId());

        Passenger passenger = new Passenger();
        passenger.setPassengerId(putTicketDto.passengerId());
        Flight flight = new Flight();
        flight.setFlightId(putTicketDto.flightId());

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);
        return ticket;
    }
}
