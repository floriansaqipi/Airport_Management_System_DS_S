package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCargoDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;

import java.util.List;

public interface CargoService {
    Cargo save(PostCargoDto postCargoDto);
    Cargo save(PutCargoDto putCargoDto);
    Cargo findById(Integer theId);
    List<Cargo> findAll();
    void deleteById(Integer theId);
}
