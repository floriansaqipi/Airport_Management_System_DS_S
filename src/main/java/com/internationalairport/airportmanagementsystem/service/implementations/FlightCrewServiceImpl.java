package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FlightCrewRepository;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightCrewServiceImpl implements FlightCrewService {

    private FlightCrewRepository flightCrewRepository;

    @Autowired
    public FlightCrewServiceImpl(FlightCrewRepository flightCrewRepository) {
        this.flightCrewRepository = flightCrewRepository;
    }

    @Override
    public FlightCrew save(FlightCrew flightCrew) {
        return flightCrewRepository.save(flightCrew);
    }

    @Override
    public FlightCrew findById(Integer flightCrewId) {
        Optional<FlightCrew> result = flightCrewRepository.findById(flightCrewId);
        FlightCrew theFlightCrew = null;
        if(result.isPresent()){
            theFlightCrew = result.get();
        }
        else{
            throw new RuntimeException("Did not find flight crew id - "+flightCrewId);
        }
        return theFlightCrew;
    }

    @Override
    public List<FlightCrew> findAll() {
        return flightCrewRepository.findAll();
    }

    @Override
    public void deleteById(Integer flightCrewId) {
        flightCrewRepository.deleteById(flightCrewId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows = (int) flightCrewRepository.count();
        flightCrewRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}

