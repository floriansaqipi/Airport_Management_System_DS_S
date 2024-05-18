package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AirportRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.mappers.AirportMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepository airportRepository;
    private AirportMapper airportMapper;

    @Autowired
    public AirportServiceImpl(AirportRepository theAirportRepository, AirportMapper theAirportMapper) {
        airportRepository = theAirportRepository;
        airportMapper = theAirportMapper;
    }

    @Override
    public Airport save(PostAirportDto postAirportDto) {
        Airport airport = airportMapper.postToAirport(postAirportDto);
        return airportRepository.save(airport);
    }

    @Override
    public Airport save(PutAirportDto putAirportDto) {
        Airport airport = airportMapper.putToAirport(putAirportDto);
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
