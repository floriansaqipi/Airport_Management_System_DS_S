package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "flight_crews")
public class FlightCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Integer crewId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "role", length = 50)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    // Constructors
    public FlightCrew() {
    }

    public FlightCrew(Integer crewId, Integer flightId, Integer employeeId, String role, Flight flight, Employee employee) {
        this.crewId = crewId;
        this.flightId = flightId;
        this.employeeId = employeeId;
        this.role = role;
        this.flight = flight;
        this.employee = employee;
    }

    // Getters and Setters
    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

