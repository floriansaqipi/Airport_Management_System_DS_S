package com.internationalairport.airportmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Integer aircraftId;

    @Column(name = "tail_number", unique = true, nullable = false, length = 10)
    private String tailNumber;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    // Constructors, Getters, and Setters
    public Aircraft() {
    }

    public Aircraft(Integer aircraftId, String tailNumber, String model, Integer capacity, Airline airline) {
        this.aircraftId = aircraftId;
        this.tailNumber = tailNumber;
        this.model = model;
        this.capacity = capacity;
        this.airline = airline;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}

