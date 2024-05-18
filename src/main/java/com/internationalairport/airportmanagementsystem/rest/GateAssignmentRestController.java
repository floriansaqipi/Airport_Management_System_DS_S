package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GateAssignmentRestController {

    private final GateAssignmentService gateAssignmentService;

    @Autowired
    public GateAssignmentRestController(GateAssignmentService gateAssignmentService) {
        this.gateAssignmentService = gateAssignmentService;
    }

    @GetMapping("/public/gate_assignments")
    public List<GateAssignment> findAllGateAssignments() {
        return gateAssignmentService.findAll();
    }

    @GetMapping("/public/gate_assignments/{gateAssignmentId}")
    public GateAssignment getGateAssignmentById(@PathVariable int gateAssignmentId) {
        return gateAssignmentService.findById(gateAssignmentId);
    }

    @PostMapping("/private/gate_assignments")
    public GateAssignment addGateAssignment(@RequestBody PostGateAssignmentDto postGateAssignmentDto) {
        return gateAssignmentService.save(postGateAssignmentDto);
    }

    @PutMapping("/private/gate_assignments")
    public GateAssignment updateGateAssignment(@RequestBody PutGateAssignmentDto putGateAssignmentDto) {
        return gateAssignmentService.save(putGateAssignmentDto);
    }

    @DeleteMapping("/private/gate_assignments/{gateAssignmentId}")
    public String deleteGateAssignmentById(@PathVariable int gateAssignmentId) {
        gateAssignmentService.deleteById(gateAssignmentId);
        return "Deleted gate assignment id - " + gateAssignmentId;
    }

    @DeleteMapping("/private/gate_assignments")
    public String deleteAllGateAssignments() {
        return gateAssignmentService.deleteAll();
    }
}
