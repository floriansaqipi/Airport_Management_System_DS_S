package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.service.interfaces.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AircraftRestController {

    private AircraftService aircraftService;

    @Autowired
    public AircraftRestController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/aircrafts")
    public List<Aircraft> findAll() {
        return aircraftService.findAll();
    }

    @GetMapping("/aircrafts/{aircraftId}")
    public Aircraft getAircraftById(@PathVariable int aircraftId) {
        Aircraft aircraft = aircraftService.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found for id - " + aircraftId);
        }
        return aircraft;
    }

    @PostMapping("/aircrafts")
    public Aircraft addAircraft(@RequestBody PostAircraftDto postAircraftDto) {
        return aircraftService.save(postAircraftDto);
    }

    @PutMapping("/aircrafts")
    public Aircraft updateAircraft(@RequestBody PostAircraftDto postAircraftDto) {
        return aircraftService.save(postAircraftDto);
    }

    @DeleteMapping("/aircrafts/{aircraftId}")
    public String deleteAircraftById(@PathVariable int aircraftId) {
        Aircraft aircraft = aircraftService.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found for id - " + aircraftId);
        }
        aircraftService.deleteById(aircraftId);
        return "Deleted aircraft with id - " + aircraftId;
    }

    @DeleteMapping("/aircrafts")
    public String deleteAllAircrafts() {
        return aircraftService.deleteAll();
    }
}
