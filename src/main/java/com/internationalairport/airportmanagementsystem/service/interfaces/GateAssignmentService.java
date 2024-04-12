package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.GateAssignment;

import java.util.List;

public interface GateAssignmentService {
    GateAssignment save(GateAssignment gateAssignment);
    GateAssignment findById(Integer id);
    List<GateAssignment> findAll();
    void deleteById(Integer id);
    String deleteAll();
}

