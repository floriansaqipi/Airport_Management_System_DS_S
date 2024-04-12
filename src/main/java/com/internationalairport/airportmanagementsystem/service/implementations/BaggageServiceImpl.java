package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.BaggageRepository;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.rest.BaggageRestController;
import com.internationalairport.airportmanagementsystem.service.interfaces.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaggageServiceImpl implements BaggageService {

    private BaggageRepository baggageRepository;

    @Autowired
    public BaggageServiceImpl(BaggageRepository theBaggageRepository){
        baggageRepository = theBaggageRepository;
    }
    @Override
    public Baggage save(Baggage theBaggage) {
        return baggageRepository.save(theBaggage);
    }

    @Override
    public Baggage findById(Integer theId) {
        Optional<Baggage> result = baggageRepository.findById(theId);
        Baggage theBaggage = null;
        if (result.isPresent()) {
            theBaggage = result.get();
        }
        else {
            throw new RuntimeException("Did not find Baggage id - " + theId);
        }
        return theBaggage;
    }

    @Override
    public List<Baggage> findAll() {
        return baggageRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        baggageRepository.deleteById(theId);
    }
}
