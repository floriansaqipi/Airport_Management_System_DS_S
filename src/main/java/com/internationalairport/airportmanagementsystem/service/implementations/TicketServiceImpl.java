package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.TicketRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostTicketDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.mappers.TicketMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    private TicketMapper ticketMapper;

    @Autowired
    public TicketServiceImpl(TicketRepository theTicketRepository, TicketMapper theTicketMapper){
        ticketRepository = theTicketRepository;
        ticketMapper = theTicketMapper;
    }
    @Override
    public Ticket save(PostTicketDto postTicketDto) {
        Ticket ticket = ticketMapper.postToTicket(postTicketDto);
//        ticket.setBoardingPass(new BoardingPass("sasf", LocalDateTime.now()));
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket save(PutTicketDto putTicketDto) {
        Ticket ticket = ticketMapper.putToTicket(putTicketDto);
        return ticketRepository.save(ticket);
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
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findByPassengerId(Integer passengerId) {
        return ticketRepository.findByPassengerId(passengerId);
    }

    @Override
    public void deleteById(Integer theId) {
        ticketRepository.deleteById(theId);
    }
}
