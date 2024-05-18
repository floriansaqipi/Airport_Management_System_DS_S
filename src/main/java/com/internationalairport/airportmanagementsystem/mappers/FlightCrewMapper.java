//package com.internationalairport.airportmanagementsystem.mappers;
//
//import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
//import com.internationalairport.airportmanagementsystem.entities.Employee;
//import com.internationalairport.airportmanagementsystem.entities.Flight;
//import com.internationalairport.airportmanagementsystem.entities.FlightCrew;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FlightCrewMapper {
//
//    public FlightCrew postToFlightCrew(PostFlightCrewDto postFlightCrewDto){
//        FlightCrew flightCrew = new FlightCrew();
//
//        Flight flight = new Flight();
//        flight.setFlightId(postFlightCrewDto.flightId());
//
//        Employee employee = new Employee();
//        employee.setId(postFlightCrewDto.employeeId());
//
//        flightCrew.setFlight(flight);
//        flightCrew.setEmployee(employee);
//        return flightCrew;
//    }
//}
