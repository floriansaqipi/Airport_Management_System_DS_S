package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {
    public Flight postToFlight(PostFlightDto postFlightDto) {
        Flight flight = new Flight(
                postFlightDto.flightNumber(),
                postFlightDto.departureTime(),
                postFlightDto.arrivalTime()
        );
        flight.setFlightId(0);

        if(postFlightDto.aircraftId() != null){
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(postFlightDto.aircraftId());
            flight.setAircraft(aircraft);
        }

        if(postFlightDto.employeeIds() != null && !postFlightDto.employeeIds().isEmpty()){
            for (Integer employeeId: postFlightDto.employeeIds()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                flight.addEmployee(employee);
            }
        }
        return flight;
    }


    public Flight putToFlight(PutFlightDto putFlightDto) {
        Flight flight = new Flight(
                putFlightDto.flightNumber(),
                putFlightDto.departureTime(),
                putFlightDto.arrivalTime()
        );
        flight.setFlightId(putFlightDto.flightId());

        if(putFlightDto.aircraftId() != null){
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(putFlightDto.aircraftId());
            flight.setAircraft(aircraft);
        }

        if(putFlightDto.employeeIds() != null && !putFlightDto.employeeIds().isEmpty()){
            for (Integer employeeId: putFlightDto.employeeIds()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                flight.addEmployee(employee);
            }
        }
        return flight;
    }
}
