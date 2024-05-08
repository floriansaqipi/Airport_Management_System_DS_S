package com.internationalairport.airportmanagementsystem.entities;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_id")
    private Integer parkingId;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "rate")
    private Double rate;

    public Parking() {
    }

    public Parking(String location, Integer capacity, Double rate) {
        this.location = location;
        this.capacity = capacity;
        this.rate = rate;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingId=" + parkingId +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", rate=" + rate +
                '}';
    }
}
