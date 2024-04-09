package com.internationalairport.airportmanagementsystem.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport_number", unique = true, nullable = false)
    private String passportNumber;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "contact_details")
    private String contactDetails;

    // Constructors, getters, and setters
    public Passenger() {
    }

    public Passenger(String name, String passportNumber, String nationality, String contactDetails) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.contactDetails = contactDetails;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
