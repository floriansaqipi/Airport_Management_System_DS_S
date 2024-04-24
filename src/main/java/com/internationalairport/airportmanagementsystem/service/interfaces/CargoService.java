package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.PostCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;

import java.util.List;

public interface CargoService {
    Cargo save(PostCargoDto postCargoDto);
    Cargo findById(Integer theId);
    List<Cargo> findAll();
    void deleteById(Integer theId);
}
