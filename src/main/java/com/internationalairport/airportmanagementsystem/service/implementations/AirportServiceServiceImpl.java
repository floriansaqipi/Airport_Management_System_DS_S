package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AirportServiceRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportServiceDto;
import com.internationalairport.airportmanagementsystem.entities.AirportService;
import com.internationalairport.airportmanagementsystem.mappers.AirportServiceMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceServiceImpl implements AirportServiceService {
    private AirportServiceRepository airportServiceRepository;
    private AirportServiceMapper airportServiceMapper;
    @Autowired
    public AirportServiceServiceImpl(AirportServiceRepository theAirportServiceRepository, AirportServiceMapper theAirportServiceMapper)
    {
        airportServiceRepository=theAirportServiceRepository;
        airportServiceMapper=theAirportServiceMapper;
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
    public AirportService save(PostAirportServiceDto postAirportServiceDto) {
        AirportService airportService = airportServiceMapper.postToAirportService(postAirportServiceDto);
        return airportServiceRepository.save(airportService);
    }

    @Override
    public AirportService save(PutAirportServiceDto putAirportServiceDto) {
        AirportService airportService = airportServiceMapper.putToAirportService(putAirportServiceDto);
        return airportServiceRepository.save(airportService);
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
