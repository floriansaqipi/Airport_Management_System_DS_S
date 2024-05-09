//package com.internationalairport.airportmanagementsystem.daos;
//
//import com.internationalairport.airportmanagementsystem.entities.FlightCrew;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface FlightCrewRepository extends JpaRepository<FlightCrew, Integer> {
//    FlightCrew findByFlightIdAndEmployeeId(Integer flightId, Integer employeeId);
//    void deleteByFlightIdAndEmployeeId(Integer flightId, Integer employeeId);
//}