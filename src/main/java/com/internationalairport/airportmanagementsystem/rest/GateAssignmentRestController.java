package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.service.interfaces.GateAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            description = "Endpoint to get all gate assignments",
            summary = "Retrieve all gate assignments",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all gate assignments",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/gate_assignments")
    public List<GateAssignment> findAllGateAssignments() {
        return gateAssignmentService.findAll();
    }

    @Operation(
            description = "Endpoint to get a gate assignment by ID",
            summary = "Retrieve a gate assignment by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Gate Assignment ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/gate_assignments/{gateAssignmentId}")
    public GateAssignment getGateAssignmentById(@PathVariable int gateAssignmentId) {
        return gateAssignmentService.findById(gateAssignmentId);
    }

    @Operation(
            description = "Endpoint to add a new gate assignment",
            summary = "Add a new gate assignment",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/gate_assignments")
    public GateAssignment addGateAssignment(@RequestBody PostGateAssignmentDto postGateAssignmentDto) {
        return gateAssignmentService.save(postGateAssignmentDto);
    }


    @Operation(
            description = "Endpoint to update a gate assignment",
            summary = "Update a gate assignment",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/gate_assignments")
    public GateAssignment updateGateAssignment(@RequestBody PutGateAssignmentDto putGateAssignmentDto) {
        return gateAssignmentService.save(putGateAssignmentDto);
    }

    @Operation(
            description = "Endpoint to delete a gate assignment by ID",
            summary = "Delete a gate assignment by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Gate Assignment ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/gate_assignments/{gateAssignmentId}")
    public String deleteGateAssignmentById(@PathVariable int gateAssignmentId) {
        gateAssignmentService.deleteById(gateAssignmentId);
        return "Deleted gate assignment id - " + gateAssignmentId;
    }

    @Operation(
            description = "Endpoint to delete all gate assignments",
            summary = "Delete all gate assignments",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all gate assignments",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/gate_assignments")
    public String deleteAllGateAssignments() {
        return gateAssignmentService.deleteAll();
    }
}
