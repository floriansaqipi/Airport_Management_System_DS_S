package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "boarding_passes")
public class BoardingPass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boarding_pass_id")
    private Integer boardingPassId;

    @Column(name = "gate")
    private String gate;

    @Column(name = "boarding_time", nullable = false)
    private LocalDateTime boardingTime;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    @JsonBackReference
    private Ticket ticket;

    // Constructors

    public BoardingPass() {
        // Default constructor
    }

    public BoardingPass( String gate, LocalDateTime boardingTime) {

        this.gate = gate;
        this.boardingTime = boardingTime;
    }

    // Getters and Setters

    public Integer getBoardingPassId() {
        return boardingPassId;
    }

    public void setBoardingPassId(Integer boardingPassId) {
        this.boardingPassId = boardingPassId;
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

    @Override
    public String toString() {
        return "BoardingPass{" +
                "boardingPassId=" + boardingPassId +
                ", gate='" + gate + '\'' +
                ", boardingTime=" + boardingTime +
                '}';
    }
}
