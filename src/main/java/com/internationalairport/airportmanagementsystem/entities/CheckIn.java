package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "check_in")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_in_id")
    private Integer checkInId;

    @Column(name = "check_in_time")
    private Date checkInTime;

    @Column(name = "desk_number")
    private Integer deskNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "passenger_id")

    private Passenger passenger;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "flight_id")

    private Flight flight;

    // Constructors
    public CheckIn() {
    }

    public CheckIn(Date checkInTime, Integer deskNumber) {
        this.checkInTime = checkInTime;
        this.deskNumber = deskNumber;
    }

    // Getters and Setters
    public Integer getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(Integer checkInId) {
        this.checkInId = checkInId;
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

    @Override
    public String toString() {
        return "CheckIn{" +
                "deskNumber=" + deskNumber +
                ", checkInTime=" + checkInTime +
                ", checkInId=" + checkInId +
                '}';
    }
}

