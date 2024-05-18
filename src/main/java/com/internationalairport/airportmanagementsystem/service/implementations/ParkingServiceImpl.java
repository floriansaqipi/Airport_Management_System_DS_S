package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.ParkingRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
import com.internationalairport.airportmanagementsystem.entities.Parking;
import com.internationalairport.airportmanagementsystem.mappers.ParkingMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    private ParkingRepository parkingRepository;
    private ParkingMapper parkingMapper;
    @Autowired
    public ParkingServiceImpl (ParkingRepository theParkingRepository, ParkingMapper theParkingMapper)
    {
        parkingRepository=theParkingRepository;
        parkingMapper=theParkingMapper;
    }


    @Override
    public Parking save(PostParkingDto postParkingDto) {
        Parking parking = parkingMapper.postToParking(postParkingDto);
        return parkingRepository.save(parking);
    }

    @Override
    public Parking save(PutParkingDto putParkingDto) {
        Parking parking = parkingMapper.putToParking(putParkingDto);
        return parkingRepository.save(parking);
    }

    @Override
    public Parking findById(Integer theId) {
        Optional<Parking> result = parkingRepository.findById(theId);
        Parking parking=null;
        if(result.isPresent()){
            parking = result.get();
        }else{
            throw new RuntimeException("Did not found parking id - "+theId);
        }
        return parking;
    }

    @Override
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        parkingRepository.deleteById(theId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows= (int)parkingRepository.count();
        parkingRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}

