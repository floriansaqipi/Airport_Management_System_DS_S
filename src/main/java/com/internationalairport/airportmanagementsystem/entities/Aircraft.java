package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Integer aircraftId;

    @Column(name = "tail_number")
    private String tailNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    @JsonIgnoreProperties({"aircrafts"})
    private Airline airline;

    @OneToMany(mappedBy = "aircraft")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargos", "employees"})
    private List<Flight> flights;

    @OneToMany(mappedBy = "aircraft")
    @JsonIgnoreProperties({"aircraft"})
    private List<Maintenance> maintenances;

    // Constructors, Getters, and Setters
    public Aircraft() {
    }

    public Aircraft(String tailNumber, String model, Integer capacity) {
        this.tailNumber = tailNumber;
        this.model = model;
        this.capacity = capacity;
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

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftId=" + aircraftId +
                ", tailNumber='" + tailNumber + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight tempFlight) {
        if (flights == null){
            flights = new ArrayList<>();
        }

        flights.add(tempFlight);

        tempFlight.setAircraft(this);
    }

    public void addMaintenance(Maintenance tempMaintenance) {
        if (maintenances == null){
            maintenances = new ArrayList<>();
        }

        maintenances.add(tempMaintenance);

        tempMaintenance.setAircraft(this);
    }
}

