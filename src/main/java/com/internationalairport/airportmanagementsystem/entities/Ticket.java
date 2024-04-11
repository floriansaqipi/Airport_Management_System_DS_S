package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "class")
    private String ticketClass;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private BoardingPass boardingPass;

    public Ticket() {
    }

    public Ticket(String seatNumber, String ticketClass, Double price) {
        this.seatNumber = seatNumber;
        this.ticketClass = ticketClass;
        this.price = price;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", seatNumber='" + seatNumber + '\'' +
                ", ticketClass='" + ticketClass + '\'' +
                ", price=" + price +
                '}';
    }
}