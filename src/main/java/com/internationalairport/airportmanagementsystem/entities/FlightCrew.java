//package com.internationalairport.airportmanagementsystem.entities;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "flight_crews")
//public class FlightCrew {
//
//    @EmbeddedId
//    private FlightCrewId flightCrewId;
//
//
//    @ManyToOne
//    @MapsId("employeeId")
//    @JsonManagedReference
//    private Employee employee;
//
//
//    @ManyToOne
//    @MapsId("flightId")
//    @JsonManagedReference
//    private Flight flight;
//
//
//
//    // Constructors
//    public FlightCrew() {
//    }
//    public Flight getFlight() {
//        return flight;
//    }
//    public void setFlight(Flight flight) {
//        this.flight = flight;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    @Override
//    public String toString() {
//        return "FlightCrew{" +
//                "employee=" + employee +
//                ", flight=" + flight +
//                '}';
//    }
//
//}
