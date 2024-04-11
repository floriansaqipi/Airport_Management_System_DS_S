package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.Ticket;

import java.util.List;

public interface TicketService {
    Ticket save(Ticket theTicket);
    Ticket findById(Integer theId);
    List<Ticket> findAll();
    void deleteById(Integer theId);
}
