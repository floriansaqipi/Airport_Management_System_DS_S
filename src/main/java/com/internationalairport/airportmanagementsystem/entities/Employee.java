package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "contact_info")
    private String contactInfo;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "airport_id")
    @JsonManagedReference
    private Airport airport;

    @OneToMany(mappedBy = "employee",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    private List<FlightCrew> flightCrews;

    // Constructors, getters, and setters
    public Employee() {
    }

    public Employee(String name, String role, String contactInfo) {
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }

    public List<FlightCrew> getFlightCrews() {
        return flightCrews;
    }

    public void setFlightCrews(List<FlightCrew> flightCrews) {
        this.flightCrews = flightCrews;
    }

    // define convenient methods
    public void addFlightCrew(FlightCrew tempFlightCrew) {
        if( flightCrews == null) {
            flightCrews = new ArrayList<>();
        }

        flightCrews.add(tempFlightCrew);

        tempFlightCrew.setEmployee(this);
    }
}
