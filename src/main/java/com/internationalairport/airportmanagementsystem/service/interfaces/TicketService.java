package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.PostTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Ticket;

import java.util.List;

public interface TicketService {
    Ticket save(PostTicketDto postTicketDto);
    Ticket findById(Integer theId);
    List<Ticket> findAll();
    void deleteById(Integer theId);
}
