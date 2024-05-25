package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Integer airportId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "location_city")
    private String locationCity;

    @Column(name = "location_country")
    private String locationCountry;

    @OneToMany(mappedBy = "departureAirport")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargos", "employees"})
    private List<Flight> departures;

    @OneToMany(mappedBy = "arrivalAirport")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargos", "employees"})
    private List<Flight> arrivals;

    @PreRemove
    public void preRemove(){
        for(Flight departure : departures) {
            departure.setDepartureAirport(null);
        }
        for(Flight arrival : arrivals) {
            arrival.setArrivalAirport(null);
        }
    }

    // Constructors, Getters, and Setters
    public Airport() {
    }

    public Airport(String code, String name, String locationCity, String locationCountry) {
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

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                '}';
    }

    public List<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Flight> departures) {
        this.departures = departures;
    }

    public List<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Flight> arrivals) {
        this.arrivals = arrivals;
    }

    // add convenience methods for bi-directional relationship

    public void addDeparture(Flight tempDeparture) {
        if (departures == null){
            departures = new ArrayList<>();
        }

        departures.add(tempDeparture);

        tempDeparture.setDepartureAirport(this);
    }

    public void addArrival(Flight tempArrival) {
        if (departures == null){
            departures = new ArrayList<>();
        }

        departures.add(tempArrival);

        tempArrival.setArrivalAirport(this);
    }
}
