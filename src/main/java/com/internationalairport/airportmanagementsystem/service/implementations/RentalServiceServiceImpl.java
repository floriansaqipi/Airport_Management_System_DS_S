package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.RentalServiceRepository;
import com.internationalairport.airportmanagementsystem.entities.RentalService;
import com.internationalairport.airportmanagementsystem.service.interfaces.RentalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceServiceImpl implements RentalServiceService {
    private RentalServiceRepository rentalServiceRepository;
    @Autowired
    public RentalServiceServiceImpl(RentalServiceRepository theRentalServiceRepository)
    {
        rentalServiceRepository=theRentalServiceRepository;
    }
    @Override
    public RentalService save(RentalService theRentalService) {
        return rentalServiceRepository.save(theRentalService);
    }

    @Override
    public RentalService findById(Integer theId) {
        Optional<RentalService> result = rentalServiceRepository.findById(theId);
        RentalService rentalService=null;
        if(result.isPresent()){
            rentalService= result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+theId);
        }
        return rentalService;
    }

    @Override
    public List<RentalService> findAll() {
        return rentalServiceRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        rentalServiceRepository.deleteById(theId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows= (int)rentalServiceRepository.count();
        rentalServiceRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}

