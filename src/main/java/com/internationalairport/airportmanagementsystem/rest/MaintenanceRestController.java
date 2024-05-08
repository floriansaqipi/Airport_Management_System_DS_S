package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutPassengerDto;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import com.internationalairport.airportmanagementsystem.service.interfaces.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaintenanceRestController {
    private MaintenanceService maintenanceService;
    @Autowired
    public MaintenanceRestController(MaintenanceService theMaintenanceService) {
        maintenanceService = theMaintenanceService;
    }
    @GetMapping("/maintenances")
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }
    @GetMapping("/maintenances/{maintenanceId}")
    public Maintenance getMaintenance(@PathVariable int maintenanceId) {
        Maintenance theMaintenance = maintenanceService.findById(maintenanceId);
        if (theMaintenance == null) {
            throw new RuntimeException("Maintenance id not found - " + maintenanceId);
        }
        return theMaintenance;
    }
    @PostMapping("/maintenances")
    public Maintenance addMaintenance(@RequestBody PostMaintenanceDto postMaintenanceDto) {
        return maintenanceService.save(postMaintenanceDto);
    }
    @PutMapping("/maintenances")
    public Maintenance updateMaintenance(@RequestBody PutMaintenanceDto putMaintenanceDto) {
       return maintenanceService.save(putMaintenanceDto);
    }
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
