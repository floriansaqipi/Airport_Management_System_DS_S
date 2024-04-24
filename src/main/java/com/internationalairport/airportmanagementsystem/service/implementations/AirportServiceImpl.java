package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AirportRepository;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport findById(Integer airportId) {
        Optional<Airport> result = airportRepository.findById(airportId);
        Airport airport = null;
        if (result.isPresent()) {
            airport = result.get();
        } else {
            throw new RuntimeException("Airport with ID " + airportId + " not found");
        }
        return airport;
    }

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public void deleteById(Integer airportId) {
        airportRepository.deleteById(airportId);
    }

    @Override
    public String deleteAll() {
        int numberOfAirports = (int) airportRepository.count();
        airportRepository.deleteAll();
        return numberOfAirports + " airports have been deleted";
    }
}
