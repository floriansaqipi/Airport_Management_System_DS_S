package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostTicketDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Ticket;

import java.util.List;

public interface TicketService {
    Ticket save(PostTicketDto postTicketDto);
    Ticket save(PutTicketDto putTicketDto);
    Ticket findById(Integer theId);
    List<Ticket> findAll();
    void deleteById(Integer theId);
}
