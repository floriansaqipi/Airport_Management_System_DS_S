package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.ParkingRepository;
import com.internationalairport.airportmanagementsystem.entities.Parking;
import com.internationalairport.airportmanagementsystem.service.interfaces.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    private ParkingRepository parkingRepository;
    @Autowired
    public ParkingServiceImpl (ParkingRepository theParkingRepository)
    {
        parkingRepository=theParkingRepository;
    }
    @Override
    public Parking save(Parking theParking) {
        return parkingRepository.save(theParking);
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

