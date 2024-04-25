package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.CheckInRepository;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.service.interfaces.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;

    @Autowired
    public CheckInServiceImpl(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    @Override
    public CheckIn save(CheckIn theCheckIn) {
        return checkInRepository.save(theCheckIn);
    }

    @Override
    public CheckIn findById(Integer theId) {
        Optional<CheckIn> result = checkInRepository.findById(theId);
        return result.orElseThrow(() -> new RuntimeException("Did not find check-in with id - " + theId));
    }

    @Override
    public List<CheckIn> findAll() {
        return checkInRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        checkInRepository.deleteById(theId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows = (int) checkInRepository.count();
        checkInRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}

