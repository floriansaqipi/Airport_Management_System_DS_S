package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String _class;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonIgnoreProperties({"baggages", "feedbacks", "checkIns", "tickets", "userEntity"})
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargos", "employees"})
    private Flight flight;

    @OneToOne(mappedBy = "ticket")
    @JsonIgnoreProperties({"ticket"})
    private BoardingPass boardingPass;

    public Ticket() {
    }

    public Ticket(String seatNumber, String _class, Double price) {
        this.seatNumber = seatNumber;
        this._class = _class;
        this.price = price;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
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
                ", ticketClass='" + _class + '\'' +
                ", price=" + price +
                '}';
    }
}