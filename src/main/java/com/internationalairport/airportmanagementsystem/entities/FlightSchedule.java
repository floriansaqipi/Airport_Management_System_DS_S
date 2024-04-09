package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "scheduled_departure_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledDepartureTime;

    @Column(name = "scheduled_arrival_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledArrivalTime;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    // Constructors
    public FlightSchedule() {
    }

    public FlightSchedule(Integer scheduleId, Integer flightId, Date scheduledDepartureTime, Date scheduledArrivalTime, String status, Flight flight) {
        this.scheduleId = scheduleId;
        this.flightId = flightId;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.status = status;
        this.flight = flight;
    }

    // Getters and Setters
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Date getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(Date scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public Date getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(Date scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}

