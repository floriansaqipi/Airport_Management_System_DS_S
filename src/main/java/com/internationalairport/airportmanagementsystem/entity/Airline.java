package com.internationalairport.airportmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "airlines", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Integer airlineId;

    @Column(name = "code", unique = true, nullable = false, length = 5)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Constructors, Getters, and Setters
    public Airline() {
    }

    public Airline(Integer airlineId, String code, String name) {
        this.airlineId = airlineId;
        this.code = code;
        this.name = name;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

