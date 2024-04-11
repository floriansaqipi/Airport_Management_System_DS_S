package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirlineRestController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineRestController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/airlines")
    public List<Airline> findAllAirlines() {
        return airlineService.findAll();
    }

    @GetMapping("/airlines/{id}")
    public Airline getAirlineById(@PathVariable Integer id) {
        return airlineService.findById(id);
    }

    @PostMapping("/airlines")
    public Airline addAirline(@RequestBody Airline airline) {
        airline.setAirlineId(0);
        return airlineService.save(airline);
    }

    @PutMapping("/airlines")
    public Airline updateAirline(@RequestBody Airline airline) {
        return airlineService.save(airline);
    }

    @DeleteMapping("/airlines/{id}")
    public String deleteAirlineById(@PathVariable Integer id) {
        airlineService.deleteById(id);
        return "Deleted airline with id - " + id;
    }

    @DeleteMapping("/airlines")
    public String deleteAllAirlines() {
        return airlineService.deleteAll();
    }
}
