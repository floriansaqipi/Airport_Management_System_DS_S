package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostPassengerDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.mappers.PassengerMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;
    private PassengerMapper passengerMapper;

    @Autowired
    public PassengerServiceImpl(PassengerRepository thePassangerRepository, PassengerMapper thePassengerMapper) {
        passengerRepository = thePassangerRepository;
        passengerMapper = thePassengerMapper;
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
    public Passenger save(PutPassengerDto putPassengerDto) {
        Passenger passenger = passengerMapper.putToPassenger(putPassengerDto);
        return passengerRepository.save(passenger);
    }
    @Override
    public Passenger save(PostPassengerDto postPassengerDto) {
        Passenger passenger = passengerMapper.postToPassenger(postPassengerDto);
        return passengerRepository.save(passenger);
    }
    @Override
    public void deleteById(int theId) {
        passengerRepository.deleteById(theId);
    }
}


