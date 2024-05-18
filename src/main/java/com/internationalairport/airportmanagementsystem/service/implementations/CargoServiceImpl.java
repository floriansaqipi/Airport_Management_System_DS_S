package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.CargoRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostCargoDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.mappers.CargoMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    private CargoRepository cargoRepository;

    private CargoMapper cargoMapper;

    @Autowired
    public CargoServiceImpl(CargoRepository theCargoRepository, CargoMapper theCargoMapper){
        cargoRepository = theCargoRepository;
        cargoMapper = theCargoMapper;
    }
    @Override
    public Cargo save(PostCargoDto postCargoDto) {
        Cargo postCargo = cargoMapper.postToCargo(postCargoDto);
        return cargoRepository.save(postCargo);
    }

    @Override
    public Cargo save(PutCargoDto putCargoDto) {
        Cargo putCargo = cargoMapper.putToCargo(putCargoDto);
        return cargoRepository.save(putCargo);
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
