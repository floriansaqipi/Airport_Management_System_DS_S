package com.internationalairport.airportmanagementsystem.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "contact_info")
    private String contactInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    // Constructors, getters, and setters
    public Employee() {
    }

    public Employee(String name, String role, String contactInfo, Airport airport) {
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
        this.airport = airport;
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
}
