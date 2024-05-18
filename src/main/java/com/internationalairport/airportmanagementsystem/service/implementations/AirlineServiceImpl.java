package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AirlineRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAirlineDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirlineDto;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.mappers.AirlineMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository airlineRepository;
    private AirlineMapper airlineMapper;

    @Autowired
    public AirlineServiceImpl(AirlineRepository theAirlineRepository, AirlineMapper theAirlineMapper) {
        airlineRepository = theAirlineRepository;
        airlineMapper = theAirlineMapper;
    }

    @Override
    public Airline save(PostAirlineDto postAirlineDto) {
        Airline airline = airlineMapper.postToAirline(postAirlineDto);
        return airlineRepository.save(airline);
    }

    @Override
    public Airline save(PutAirlineDto putAirlineDto) {
        Airline airline = airlineMapper.putToAirline(putAirlineDto);
        return airlineRepository.save(airline);
    }

    @Override
    public Airline findById(Integer id) {
        Optional<Airline> result = airlineRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Airline not found with ID: " + id);
        }
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public String deleteAll() {
        int numberOfAirlines = (int) airlineRepository.count();
        airlineRepository.deleteAll();
        return numberOfAirlines + " airlines have been deleted";
    }
}
