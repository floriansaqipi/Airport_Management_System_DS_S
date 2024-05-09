package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FlightRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Flight;

import com.internationalairport.airportmanagementsystem.service.interfaces.EmployeeService;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightCrewService;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightCrewServiceImpl implements FlightCrewService {

    private FlightRepository flightRepository;
    private FlightService flightService;


    private EmployeeService employeeService;

    @Autowired
    public FlightCrewServiceImpl(
            FlightRepository flightRepository,
            FlightService flightService,
            EmployeeService employeeService
            )
    {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
        this.employeeService = employeeService;
    }

    @Override
    public Flight save(PostFlightCrewDto postFlightCrewDto) {
        Flight flight = flightService.findById(postFlightCrewDto.flightId());
        Employee employee = employeeService.findById(postFlightCrewDto.employeeId());
        flight.addEmployee(employee);
        return flightRepository.save(flight);
    }

    @Override
    public void deleteByFlightIdAndEmployeeId(Integer flightId, Integer employeeId) {
        Flight flight = flightService.findById(flightId);
        Employee employee = employeeService.findById(employeeId);
        flight.getEmployees().remove(employee);
        flightRepository.save(flight);
    }
}

