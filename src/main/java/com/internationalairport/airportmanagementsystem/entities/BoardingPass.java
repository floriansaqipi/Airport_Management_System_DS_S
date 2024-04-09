package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "boarding_passes")
public class BoardingPass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boarding_pass_id")
    private Long boardingPassId;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "gate")
    private String gate;

    @Column(name = "boarding_time", nullable = false)
    private LocalDateTime boardingTime;

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id", insertable = false, updatable = false)
    private Ticket ticket;

    // Constructors

    public BoardingPass() {
        // Default constructor
    }

    public BoardingPass(Long ticketId, String gate, LocalDateTime boardingTime) {
        this.ticketId = ticketId;
        this.gate = gate;
        this.boardingTime = boardingTime;
    }

    // Getters and Setters

    public Long getBoardingPassId() {
        return boardingPassId;
    }

    public void setBoardingPassId(Long boardingPassId) {
        this.boardingPassId = boardingPassId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
