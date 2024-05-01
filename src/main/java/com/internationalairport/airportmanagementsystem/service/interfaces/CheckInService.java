package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCheckInDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCheckInDto;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;

import java.util.List;

public interface CheckInService {
    CheckIn save(PostCheckInDto postCheckInDto);
    CheckIn save(PutCheckInDto putCheckInDto);
    CheckIn findById(Integer id);
    List<CheckIn> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
