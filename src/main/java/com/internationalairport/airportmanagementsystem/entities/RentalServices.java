package com.internationalairport.airportmanagementsystem.entities;
import jakarta.persistence.*;

@Entity
public class RentalServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "rate")
    private Double rate;

    public RentalServices() {
    }

    public RentalServices(String type, String description, Double rate) {
        this.type = type;
        this.description = description;
        this.rate = rate;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RentalService{" +
                "rentalId=" + rentalId +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}
