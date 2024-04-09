package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baggage_id")
    private Long baggageId;

    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "weight")
    private Double weight;

    // Constructors, getters, and setters

    public Baggage() {
        // Default constructor
    }

    public Baggage(Long passengerId, Long flightId, Double weight) {
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.weight = weight;
    }

    // Getters and Setters

    public Long getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(Long baggageId) {
        this.baggageId = baggageId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}