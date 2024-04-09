package com.internationalairport.airportmanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Constructors, getters, and setters
    public Maintenance() {
    }

    public Maintenance(Aircraft aircraft, LocalDateTime date, String type, String description) {
        this.aircraft = aircraft;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
