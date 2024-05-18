package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.CheckInRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostCheckInDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCheckInDto;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.mappers.CheckInMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckInService {

    private CheckInRepository checkInRepository;
    private CheckInMapper checkInMapper;

    @Autowired
    public CheckInServiceImpl(CheckInRepository theCheckInRepository, CheckInMapper theCheckInMapper){
        checkInRepository = theCheckInRepository;
        checkInMapper = theCheckInMapper;
    }

    @Override
    public CheckIn save(PostCheckInDto postCheckInDto) {
        CheckIn postCheckIn = checkInMapper.postToCheckIn(postCheckInDto);
        return checkInRepository.save(postCheckIn);
    }

    @Override
    public CheckIn save(PutCheckInDto putCheckInDto) {
        CheckIn putCheckIn = checkInMapper.putToCheckIn(putCheckInDto);
        return checkInRepository.save(putCheckIn);
    }

    @Override
    public CheckIn findById(Integer theId) {
        Optional<CheckIn> result = checkInRepository.findById(theId);
        return result.orElseThrow(() -> new RuntimeException("Did not find CheckIn id - " + theId));
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

