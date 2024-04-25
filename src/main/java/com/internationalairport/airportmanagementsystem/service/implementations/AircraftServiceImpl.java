package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AircraftRepository;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.service.interfaces.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftServiceImpl implements AircraftService {

    private AircraftRepository aircraftRepository;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public Aircraft save(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft findById(Integer aircraftId) {
        Optional<Aircraft> result = aircraftRepository.findById(aircraftId);
        Aircraft aircraft = null;
        if (result.isPresent()) {
            aircraft = result.get();
        } else {
            throw new RuntimeException("Aircraft with ID " + aircraftId + " not found");
        }
        return aircraft;
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
