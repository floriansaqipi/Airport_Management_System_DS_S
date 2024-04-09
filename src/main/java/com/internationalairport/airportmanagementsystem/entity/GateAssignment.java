package com.internationalairport.airportmanagementsystem.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gate_assignments")
public class GateAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "gate", length = 10)
    private String gate;

    @Column(name = "assignment_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    // Constructors
    public GateAssignment() {
    }

    public GateAssignment(Integer assignmentId, Integer flightId, String gate, Date assignmentTime, Flight flight) {
        this.assignmentId = assignmentId;
        this.flightId = flightId;
        this.gate = gate;
        this.assignmentTime = assignmentTime;
        this.flight = flight;
    }

    // Getters and Setters
    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Date getAssignmentTime() {
        return assignmentTime;
    }

    public void setAssignmentTime(Date assignmentTime) {
        this.assignmentTime = assignmentTime;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}

