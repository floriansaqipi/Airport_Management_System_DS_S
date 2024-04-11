package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;

import java.util.List;

public interface SecurityCheckpointService {
    SecurityCheckpoint save(SecurityCheckpoint theSecurityCheckpoint);
    SecurityCheckpoint findById(Integer id);
    List<SecurityCheckpoint> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
