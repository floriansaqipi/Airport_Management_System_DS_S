package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airlines", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Integer airlineId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "airline")
    @JsonIgnoreProperties({"airline", "flights", "maintenances"})
    private List<Aircraft> aircrafts;

    // Constructors, Getters, and Setters
    public Airline() {
    }

    public Airline(String code, String name) {
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

    @Override
    public String toString() {
        return "Airline{" +
                "airlineId=" + airlineId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public void addAircraft(Aircraft tempAircraft) {
        if (aircrafts == null){
            aircrafts = new ArrayList<>();
        }

        aircrafts.add(tempAircraft);

        tempAircraft.setAirline(this);
    }
}

