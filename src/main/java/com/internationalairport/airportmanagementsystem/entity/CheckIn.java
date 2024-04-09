package com.internationalairport.airportmanagementsystem.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "check_in")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_in_id")
    private Integer checkInId;

    @Column(name = "passenger_id")
    private Integer passengerId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "check_in_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;

    @Column(name = "desk_number")
    private Integer deskNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    // Constructors
    public CheckIn() {
    }

    public CheckIn(Integer checkInId, Integer passengerId, Integer flightId, Date checkInTime, Integer deskNumber, Passenger passenger, Flight flight) {
        this.checkInId = checkInId;
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.checkInTime = checkInTime;
        this.deskNumber = deskNumber;
        this.passenger = passenger;
        this.flight = flight;
    }

    // Getters and Setters
    public Integer getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(Integer checkInId) {
        this.checkInId = checkInId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Integer getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}

