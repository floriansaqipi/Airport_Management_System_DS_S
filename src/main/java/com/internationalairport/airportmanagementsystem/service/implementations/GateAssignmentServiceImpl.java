package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.GateAssignmentRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.mappers.GateAssignmentMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateAssignmentServiceImpl implements GateAssignmentService {

    private GateAssignmentRepository gateAssignmentRepository;
    private GateAssignmentMapper gateAssignmentMapper;

    @Autowired
    public GateAssignmentServiceImpl(GateAssignmentRepository gateAssignmentRepository,
                                     GateAssignmentMapper gateAssignmentMapper) {
        this.gateAssignmentRepository = gateAssignmentRepository;
        this.gateAssignmentMapper = gateAssignmentMapper;
    }

    @Override
    public GateAssignment save(PostGateAssignmentDto postGateAssignmentDto) {
        GateAssignment gateAssignment = gateAssignmentMapper.postToGateAssignment(postGateAssignmentDto);
        return gateAssignmentRepository.save(gateAssignment);
    }

    @Override
    public GateAssignment save(PutGateAssignmentDto putGateAssignmentDto) {
        GateAssignment gateAssignment = gateAssignmentMapper.putToGateAssignment(putGateAssignmentDto);
        return gateAssignmentRepository.save(gateAssignment);
    }

    @Override
    public GateAssignment findById(Integer id) {
        Optional<GateAssignment> result = gateAssignmentRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("Did not find gate assignment id - " + id));
    }

    @Override
    public List<GateAssignment> findAll() {
        return gateAssignmentRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        gateAssignmentRepository.deleteById(id);
    }

    @Override
    public String deleteAll() {
        int numberOfRows = (int) gateAssignmentRepository.count();
        gateAssignmentRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}
