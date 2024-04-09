package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "class")
    private String ticketClass;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    public Ticket() {
    }

    public Ticket(String seatNumber, String ticketClass, Double price, Flight flight, Passenger passenger) {
        this.seatNumber = seatNumber;
        this.ticketClass = ticketClass;
        this.price = price;
        this.flight = flight;
        this.passenger = passenger;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}