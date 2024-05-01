package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gate_assignments")
public class GateAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "gate")
    private String gate;

    @Column(name = "assignment_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignmentTime;

    @OneToOne
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;

    // Constructors
    public GateAssignment() {
    }

    public GateAssignment(String gate, Date assignmentTime) {
        this.gate = gate;
        this.assignmentTime = assignmentTime;
    }

    // Getters and Setters
    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
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

    @Override
    public String toString() {
        return "GateAssignment{" +
                "assignmentTime=" + assignmentTime +
                ", gate='" + gate + '\'' +
                ", assignmentId=" + assignmentId +
                '}';
    }
}

