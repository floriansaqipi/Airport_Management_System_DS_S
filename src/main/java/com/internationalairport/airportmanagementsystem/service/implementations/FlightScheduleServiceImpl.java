package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FlightScheduleRepository;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private FlightScheduleRepository flightScheduleRepository;

    @Autowired
    public FlightScheduleServiceImpl(FlightScheduleRepository theFlightScheduleRepository){
        flightScheduleRepository = theFlightScheduleRepository;
    }

    @Override
    public FlightSchedule save(FlightSchedule theFlightSchedule) {
        return flightScheduleRepository.save(theFlightSchedule);
    }

    @Override
    public FlightSchedule findById(Integer flightScheduleId) {
        Optional<FlightSchedule> result = flightScheduleRepository.findById(flightScheduleId);
        FlightSchedule theFlightSchedule = null;
        if(result.isPresent()){
            theFlightSchedule = result.get();
        }
        else{
            throw new RuntimeException("Did not find flight schedule id - "+flightScheduleId);
        }
        return theFlightSchedule;
    }

    @Override
    public List<FlightSchedule> findAll() {
        return flightScheduleRepository.findAll();
    }

    @Override
    public void deleteById(Integer flightScheduleId) {
        flightScheduleRepository.deleteById(flightScheduleId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows = (int) flightScheduleRepository.count();
        flightScheduleRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}

