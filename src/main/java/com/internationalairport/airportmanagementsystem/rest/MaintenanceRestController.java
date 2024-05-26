package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import com.internationalairport.airportmanagementsystem.service.interfaces.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class MaintenanceRestController {
    private MaintenanceService maintenanceService;
    @Autowired
    public MaintenanceRestController(MaintenanceService theMaintenanceService) {
        maintenanceService = theMaintenanceService;
    }


    @Operation(
            description = "Endpoint to get all maintenances",
            summary = "Retrieve all maintenances",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all maintenances",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/maintenances")
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }
    @Operation(
            description = "Endpoint to get a maintenance by ID",
            summary = "Retrieve a maintenance by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Maintenance ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/maintenances/{maintenanceId}")
    public Maintenance getMaintenance(@PathVariable int maintenanceId) {
        Maintenance theMaintenance = maintenanceService.findById(maintenanceId);
        if (theMaintenance == null) {
            throw new RuntimeException("Maintenance id not found - " + maintenanceId);
        }
        return theMaintenance;
    }
    @Operation(
            description = "Endpoint to add a new maintenance",
            summary = "Add a new maintenance",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/maintenances")
    public Maintenance addMaintenance(@RequestBody PostMaintenanceDto postMaintenanceDto) {
        return maintenanceService.save(postMaintenanceDto);
    }
    @Operation(
            description = "Endpoint to update a maintenance",
            summary = "Update a maintenance",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/maintenances")
    public Maintenance updateMaintenance(@RequestBody PutMaintenanceDto putMaintenanceDto) {
       return maintenanceService.save(putMaintenanceDto);
    }
    @Operation(
            description = "Endpoint to delete a maintenance by ID",
            summary = "Delete a maintenance by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Maintenance ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/maintenances/{maintenanceId}")
    public String deleteMaintenance(@PathVariable int maintenanceId) {
        Maintenance tempMaintenance = maintenanceService.findById(maintenanceId);
        if (tempMaintenance == null) {
            throw new RuntimeException("Maintenance id not found - " + maintenanceId);
        }
        maintenanceService.deleteById(maintenanceId);
        return "Deleted maintenance id - " + maintenanceId;
    }
}
