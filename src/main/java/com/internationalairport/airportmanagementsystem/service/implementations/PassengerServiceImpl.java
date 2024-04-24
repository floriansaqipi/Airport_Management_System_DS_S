package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository thePassangerRepository) {
        passengerRepository = thePassangerRepository;
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(int theId) {
        Optional<Passenger> result = passengerRepository.findById(theId);

        Passenger thePassenger = null;

        if (result.isPresent()) {
            thePassenger = result.get();
        }
        else {
            throw new RuntimeException("Did not find passenger id - " + theId);
        }

        return thePassenger;
    }

    @Override
    public Passenger save(Passenger thePassenger) {
        return passengerRepository.save(thePassenger);
    }

    @Override
    public void deleteById(int theId) {
        passengerRepository.deleteById(theId);
    }
}


