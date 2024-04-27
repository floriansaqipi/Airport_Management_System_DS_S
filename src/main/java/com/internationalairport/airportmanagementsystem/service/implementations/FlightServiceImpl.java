package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FlightRepository;
import com.internationalairport.airportmanagementsystem.dtos.PostFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.mappers.FlightMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private FlightMapper flightMapper;

    @Autowired
    public FlightServiceImpl(FlightRepository theFlightRepository, FlightMapper theFlightMapper) {
        flightRepository = theFlightRepository;
        flightMapper = theFlightMapper;
    }

    @Override
    public Flight save(PostFlightDto postFlightDto) {
        Flight flight = flightMapper.toFlight(postFlightDto);
        return flightRepository.save(flight);
    }

    @Override
    public Flight findById(Integer flightId) {
        Optional<Flight> result = flightRepository.findById(flightId);
        Flight flight = null;
        if (result.isPresent()) {
            flight = result.get();
        } else {
            throw new RuntimeException("Flight with ID " + flightId + " not found");
        }
        return flight;
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public void deleteById(Integer flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public String deleteAll() {
        int numberOfFlights = (int) flightRepository.count();
        flightRepository.deleteAll();
        return numberOfFlights + " flights have been deleted";
    }
}
