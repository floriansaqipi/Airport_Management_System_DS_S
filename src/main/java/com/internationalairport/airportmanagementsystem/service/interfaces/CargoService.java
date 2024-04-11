package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;

import java.util.List;

public interface CargoService {
    Cargo save(Cargo theCargo);
    Cargo findById(Integer theId);
    List<Cargo> findAll();
    void deleteById(Integer theId);
}
