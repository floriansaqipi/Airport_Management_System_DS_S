package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;

import java.util.List;

public interface GateAssignmentService {
    GateAssignment save(PostGateAssignmentDto postGateAssignmentDto);
    GateAssignment save(PutGateAssignmentDto putGateAssignmentDto);
    GateAssignment findById(Integer id);
    List<GateAssignment> findAll();
    void deleteById(Integer id);
    String deleteAll();
}


