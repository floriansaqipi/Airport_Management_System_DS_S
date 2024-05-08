//package com.internationalairport.airportmanagementsystem.entities;
//
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//
//@Embeddable
//public class FlightCrewId  implements Serializable {
//
//    @Column(name = "flight_id")
//    private Integer flightId;
//    @Column(name = "employee_id")
//    private Integer employeeId;
//
//    public FlightCrewId() {
//    }
//
//    public FlightCrewId(Integer flightId, Integer employeeId) {
//        this.flightId = flightId;
//        this.employeeId = employeeId;
//    }
//
//    public Integer getFlightId() {
//        return flightId;
//    }
//
//    public void setFlightId(Integer flightId) {
//        this.flightId = flightId;
//    }
//
//    public Integer getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(Integer employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    @Override
//    public String toString() {
//        return "FlightCrewId{" +
//                "flightId=" + flightId +
//                ", employeeId=" + employeeId +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FlightCrewId that)) return false;
//        return Objects.equals(getFlightId(), that.getFlightId()) && Objects.equals(getEmployeeId(), that.getEmployeeId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getFlightId(), getEmployeeId());
//    }
//}
