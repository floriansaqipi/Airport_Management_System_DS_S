package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.CargoRepository;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    private CargoRepository cargoRepository;

    @Autowired
    public CargoServiceImpl(CargoRepository theCargoRepository){
        cargoRepository = theCargoRepository;
    }
    @Override
    public Cargo save(Cargo theCargo) {
        return cargoRepository.save(theCargo);
    }

    @Override
    public Cargo findById(Integer theId) {
        Optional<Cargo> result = cargoRepository.findById(theId);
        Cargo theCargo = null;
        if (result.isPresent()) {
            theCargo = result.get();
        }
        else {
            throw new RuntimeException("Did not find Cargo id - " + theId);
        }
        return theCargo;
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        cargoRepository.deleteById(theId);
    }
}
