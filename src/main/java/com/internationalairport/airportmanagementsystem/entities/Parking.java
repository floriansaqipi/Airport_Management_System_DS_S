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

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal rate;

    public Parking() {
    }

    public Parking(String location, Integer capacity, BigDecimal rate) {
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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
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
