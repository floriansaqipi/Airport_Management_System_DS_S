package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.service.interfaces.CargoService;
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

    @GetMapping("/ticket")
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

    @PostMapping("/ticket")
    public Ticket addTicket(@RequestBody Ticket theTicket){
        theTicket.setTicketId(0);
        Ticket ticket = ticketService.save(theTicket);
        return ticket;
    }

    @PutMapping("/ticket")
    public Ticket updateTicket(@RequestBody Ticket theTicket){
        Ticket dbTicket = ticketService.save(theTicket);
        return dbTicket;
    }

    @DeleteMapping("/ticket/{ticketId}")
    public String deleteTicket(@PathVariable int ticketId){
        Ticket tempTicket = ticketService.findById(ticketId);
        if(tempTicket == null){
            throw new RuntimeException("Ticket id not found - " + ticketId);
        }
        ticketService.deleteById(ticketId);
        return "Deleted Ticket id - " + ticketId;
    }
}
