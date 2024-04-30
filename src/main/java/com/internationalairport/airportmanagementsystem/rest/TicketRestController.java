package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostTicketDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketRestController {
    private TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService theTicketService){
        ticketService = theTicketService;
    }

    @GetMapping("/tickets")
    public List<Ticket> findAll(){
        return ticketService.findAll();
    }

    @GetMapping("/tickets/{ticketId}")
    public Ticket getTicket(@PathVariable int ticketId){
        Ticket theTicket = ticketService.findById(ticketId);
        if(theTicket == null){
            throw new RuntimeException("Ticket id not found - " + ticketId);
        }
        return theTicket;
    }

    @PostMapping("/tickets")
    public Ticket addTicket(@RequestBody PostTicketDto postTicketDto){

        return ticketService.save(postTicketDto);

    }

    @PutMapping("/tickets")
    public Ticket updateTicket(@RequestBody PutTicketDto putTicketDto){
        return ticketService.save(putTicketDto);
    }

    @DeleteMapping("/tickets/{ticketId}")
    public String deleteTicket(@PathVariable int ticketId){
        Ticket tempTicket = ticketService.findById(ticketId);
        if(tempTicket == null){
            throw new RuntimeException("Ticket id not found - " + ticketId);
        }
        ticketService.deleteById(ticketId);
        return "Deleted Ticket id - " + ticketId;
    }
}
