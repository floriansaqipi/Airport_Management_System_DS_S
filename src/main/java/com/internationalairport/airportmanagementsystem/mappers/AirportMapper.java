package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportMapper {
    public Airport toAirport(PostAirportDto postAirportDto) {
        Airport airport = new Airport(
                postAirportDto.code(),
                postAirportDto.name(),
                postAirportDto.locationCity(),
                postAirportDto.locationCountry()
        );

        List<Flight> departures = new ArrayList<>();
        postAirportDto.departures().forEach(departureId -> {
            Flight departure = new Flight();
            departure.setFlightId(departureId);
            departures.add(departure);
        });
        airport.setDepartures(departures);

        List<Flight> arrivals = new ArrayList<>();
        postAirportDto.arrivals().forEach(arrivalId -> {
            Flight arrival = new Flight();
            arrival.setFlightId(arrivalId);
            arrivals.add(arrival);
        });
        airport.setArrivals(arrivals);

        List<Employee> employees = new ArrayList<>();
        postAirportDto.employees().forEach(employeeId -> {
            Employee employee = new Employee();
            employee.setId(employeeId);
            employees.add(employee);
        });
        airport.setEmployees(employees);

        return airport;
    }
}
