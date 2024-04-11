package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.GateAssignmentRepository;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateAssignmentServiceImpl implements GateAssignmentService {

    private GateAssignmentRepository gateAssignmentRepository;

    @Autowired
    public GateAssignmentServiceImpl(GateAssignmentRepository theGateAssignmentRepository){
        gateAssignmentRepository = theGateAssignmentRepository;
    }

    @Override
    public GateAssignment save(GateAssignment theGateAssignment) {
        return gateAssignmentRepository.save(theGateAssignment);
    }

    @Override
    public GateAssignment findById(Integer gateAssignmentId) {
        Optional<GateAssignment> result = gateAssignmentRepository.findById(gateAssignmentId);
        GateAssignment theGateAssignment = null;
        if(result.isPresent()){
            theGateAssignment = result.get();
        }
        else{
            throw new RuntimeException("Did not find gate assignment id - "+gateAssignmentId);
        }
        return theGateAssignment;
    }

    @Override
    public List<GateAssignment> findAll() {
        return gateAssignmentRepository.findAll();
    }

    @Override
    public void deleteById(Integer gateAssignmentId) {
        gateAssignmentRepository.deleteById(gateAssignmentId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows = (int) gateAssignmentRepository.count();
        gateAssignmentRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}
