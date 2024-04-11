package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.FlightRepository;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight save(Flight flight) {
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
