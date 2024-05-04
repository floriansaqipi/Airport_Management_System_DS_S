package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;

import java.util.List;

public interface SecurityCheckpointService {
    SecurityCheckpoint save(PostSecurityCheckpointDto postSecurityCheckpointDto);
    SecurityCheckpoint save(PutSecurityCheckpointDto putSecurityCheckpointDto);
    SecurityCheckpoint findById(Integer id);
    List<SecurityCheckpoint> findAll();
    void deleteById(Integer theId);
    String deleteAll();
}
