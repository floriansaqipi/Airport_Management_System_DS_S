package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "scheduled_departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledDepartureTime;

    @Column(name = "scheduled_arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledArrivalTime;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;

    // Constructors
    public FlightSchedule() {
    }

    public FlightSchedule(Date scheduledDepartureTime, Date scheduledArrivalTime, String status) {
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.status = status;
    }

    // Getters and Setters
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "status='" + status + '\'' +
                ", scheduledArrivalTime=" + scheduledArrivalTime +
                ", scheduledDepartureTime=" + scheduledDepartureTime +
                ", scheduleId=" + scheduleId +
                '}';
    }
}

