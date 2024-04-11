package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.AirportServiceRepository;
import com.internationalairport.airportmanagementsystem.entities.AirportService;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceServiceImpl implements AirportServiceService {
    private AirportServiceRepository airportServiceRepository;
    @Autowired
    public AirportServiceServiceImpl(AirportServiceRepository theAirportServiceRepository)
    {
        airportServiceRepository=theAirportServiceRepository;
    }
    @Override
    public AirportService save(AirportService theAirportService) {
        return airportServiceRepository.save(theAirportService);
    }

    @Override
    public AirportService findById(Integer theId) {
        Optional<AirportService> result = airportServiceRepository.findById(theId);
        AirportService airportService=null;
        if(result.isPresent()){
            airportService = result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+theId);
        }
        return airportService;
    }

    @Override
    public List<AirportService> findAll() {
        return airportServiceRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        airportServiceRepository.deleteById(theId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows= (int)airportServiceRepository.count();
        airportServiceRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}
