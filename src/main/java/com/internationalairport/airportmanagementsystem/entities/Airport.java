package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "airports", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Integer airportId;

    @Column(name = "code", unique = true, nullable = false, length = 5)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "location_city", nullable = false, length = 255)
    private String locationCity;

    @Column(name = "location_country", nullable = false, length = 255)
    private String locationCountry;

    // Constructors, Getters, and Setters
    public Airport() {
    }

    public Airport(Integer airportId, String code, String name, String locationCity, String locationCountry) {
        this.airportId = airportId;
        this.code = code;
        this.name = name;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
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

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }
}
