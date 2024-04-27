package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AircraftRepository;
import com.internationalairport.airportmanagementsystem.dtos.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.mappers.AircraftMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftServiceImpl implements AircraftService {

    private AircraftRepository aircraftRepository;
    private AircraftMapper aircraftMapper;

    @Autowired
    public AircraftServiceImpl(AircraftRepository theAircraftRepository, AircraftMapper theAircraftMapper) {
        aircraftRepository = theAircraftRepository;
        aircraftMapper = theAircraftMapper;
    }

    @Override
    public Aircraft save(PostAircraftDto postAircraftDto) {
        Aircraft aircraft = aircraftMapper.toAircraft(postAircraftDto);
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft findById(Integer aircraftId) {
        Optional<Aircraft> result = aircraftRepository.findById(aircraftId);
        Aircraft theAircraft = null;
        if (result.isPresent()) {
            theAircraft = result.get();
        } else {
            throw new RuntimeException("Aircraft with ID " + aircraftId + " not found");
        }
        return theAircraft;
    }

    @Override
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }

    @Override
    public void deleteById(Integer aircraftId) {
        aircraftRepository.deleteById(aircraftId);
    }

    @Override
    public String deleteAll() {
        int numberOfAircrafts = (int) aircraftRepository.count();
        aircraftRepository.deleteAll();
        return numberOfAircrafts + " aircrafts have been deleted";
    }
}
