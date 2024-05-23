package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostTicketDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutTicketDto;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import com.internationalairport.airportmanagementsystem.service.interfaces.TicketService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketRestController.class)
public class TicketRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private UserEntityService userEntityService;

    @MockBean
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TicketRestController(ticketService,userEntityService,passengerService)).build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testFindAllTickets() throws Exception {
        Ticket ticket = new Ticket("1A", "Economy", 150.00);
        ticket.setTicketId(1);
        List<Ticket> tickets = Collections.singletonList(ticket);

        Mockito.when(ticketService.findAll()).thenReturn(tickets);

        mockMvc.perform(get("/api/private/tickets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].seatNumber").value("1A"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetTicketById() throws Exception {
        Ticket ticket = new Ticket("1A", "Economy", 150.00);
        ticket.setTicketId(1);

        Mockito.when(ticketService.findById(anyInt())).thenReturn(ticket);

        mockMvc.perform(get("/api/private/tickets/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seatNumber").value("1A"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAddTicket() throws Exception {
        Ticket ticket = new Ticket("1A", "Economy", 150.00);
        ticket.setTicketId(1);

        PostTicketDto postTicketDto = new PostTicketDto(1, 1, "1A", "Economy", 150.00);

        Mockito.when(ticketService.save(any(PostTicketDto.class))).thenReturn(ticket);

        mockMvc.perform(post("/api/private/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"flightId\": 1, \"passengerId\": 1, \"seatNumber\": \"1A\", \"_class\": \"Economy\", \"price\": 150.00 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seatNumber").value("1A"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateTicket() throws Exception {
        Ticket ticket = new Ticket("1A", "Economy", 150.00);
        ticket.setTicketId(1);

        PutTicketDto putTicketDto = new PutTicketDto(1, 1, 1, "1A", "Economy", 150.00, null);

        Mockito.when(ticketService.save(any(PutTicketDto.class))).thenReturn(ticket);

        mockMvc.perform(put("/api/private/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"ticketId\": 1, \"flightId\": 1, \"passengerId\": 1, \"seatNumber\": \"1A\", \"_class\": \"Economy\", \"price\": 150.00 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.seatNumber").value("1A"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteTicketById() throws Exception {
        Ticket ticket = new Ticket("1A", "Economy", 150.00);
        ticket.setTicketId(1);

        Mockito.when(ticketService.findById(anyInt())).thenReturn(ticket);
        Mockito.doNothing().when(ticketService).deleteById(anyInt());

        mockMvc.perform(delete("/api/private/tickets/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Ticket id - 1"));
    }
}

