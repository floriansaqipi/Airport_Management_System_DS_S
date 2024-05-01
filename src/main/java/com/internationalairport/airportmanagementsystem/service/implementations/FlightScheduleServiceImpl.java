package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FlightScheduleRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.mappers.FlightScheduleMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightScheduleMapper flightScheduleMapper;

    @Autowired
    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository, FlightScheduleMapper flightScheduleMapper) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.flightScheduleMapper = flightScheduleMapper;
    }

    @Override
    public FlightSchedule save(PostFlightScheduleDto postFlightScheduleDto) {
        FlightSchedule flightSchedule = flightScheduleMapper.postToFlightSchedule(postFlightScheduleDto);
        return flightScheduleRepository.save(flightSchedule);
    }

    @Override
    public FlightSchedule save(PutFlightScheduleDto putFlightScheduleDto) {
        FlightSchedule flightSchedule = flightScheduleMapper.putToFlightSchedule(putFlightScheduleDto);
        return flightScheduleRepository.save(flightSchedule);
    }

    @Override
    public FlightSchedule findById(Integer flightScheduleId) {
        Optional<FlightSchedule> result = flightScheduleRepository.findById(flightScheduleId);
        return result.orElseThrow(() -> new RuntimeException("Did not find flight schedule id - " + flightScheduleId));
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