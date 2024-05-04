package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRentalServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRentalServiceDto;
import com.internationalairport.airportmanagementsystem.entities.RentalService;

import java.util.List;

public interface RentalServiceService {
    RentalService save(PostRentalServiceDto postRentalServiceDto);
    RentalService save(PutRentalServiceDto putRentalServiceDto);
    RentalService findById(Integer id);
    List<RentalService> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
