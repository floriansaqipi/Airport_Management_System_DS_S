package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GateAssignmentRestController {

    private GateAssignmentService gateAssignmentService;

    @Autowired
    public GateAssignmentRestController(GateAssignmentService theGateAssignmentService){
        gateAssignmentService = theGateAssignmentService;
    }

    @GetMapping("/gate_assignments")
    public List<GateAssignment> findAllGateAssignments(){
        return gateAssignmentService.findAll();
    }

    @GetMapping("/gate_assignments/{gateAssignmentId}")
    public GateAssignment getGateAssignmentById(@PathVariable int gateAssignmentId){
        GateAssignment theGateAssignment = gateAssignmentService.findById(gateAssignmentId);
        if(theGateAssignment == null){
            throw new RuntimeException("Gate assignment not found - "+gateAssignmentId);
        }
        return theGateAssignment;
    }

    @PostMapping("/gate_assignments")
    public GateAssignment addGateAssignment(@RequestBody GateAssignment theGateAssignment){
        theGateAssignment.setAssignmentId(0);
        return gateAssignmentService.save(theGateAssignment);
    }

    @PutMapping("/gate_assignments")
    public GateAssignment updateGateAssignment(@RequestBody GateAssignment theGateAssignment){
        return gateAssignmentService.save(theGateAssignment);
    }

    @DeleteMapping("/gate_assignments/{gateAssignmentId}")
    public String deleteGateAssignmentById(@PathVariable int gateAssignmentId){
        GateAssignment tempGateAssignment = gateAssignmentService.findById(gateAssignmentId);
        if(tempGateAssignment == null){
            throw new RuntimeException("Gate assignment not found - "+gateAssignmentId);
        }
        gateAssignmentService.deleteById(gateAssignmentId);
        return "Deleted gate assignment id - "+gateAssignmentId;
    }

    @DeleteMapping("/gate_assignments")
    public String deleteAllGateAssignments() {
        return gateAssignmentService.deleteAll();
    }
}
