package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.TicketRepository;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository theTicketRepository){
        ticketRepository = theTicketRepository;
    }
    @Override
    public Ticket save(Ticket theTicket) {
        return ticketRepository.save(theTicket);
    }

    @Override
    public Ticket findById(Integer theId) {
        Optional<Ticket> result = ticketRepository.findById(theId);
        Ticket theTicket = null;
        if (result.isPresent()) {
            theTicket = result.get();
        }
        else {
            throw new RuntimeException("Did not find Ticket id - " + theId);
        }
        return theTicket;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer theId) {

    }
}
