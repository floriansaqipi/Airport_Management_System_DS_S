package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.PostAirlineDto;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirlineRestController {

    private AirlineService airlineService;

    @Autowired
    public AirlineRestController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/airlines")
    public List<Airline> findAll() {
        return airlineService.findAll();
    }

    @GetMapping("/airlines/{id}")
    public Airline getAirlineById(@PathVariable Integer id) {
        return airlineService.findById(id);
    }

    @PostMapping("/airlines")
    public Airline addAirline(@RequestBody PostAirlineDto postAirlineDto) {
        return airlineService.save(postAirlineDto);
    }

    @PutMapping("/airlines")
    public Airline updateAirline(@RequestBody PostAirlineDto postAirlineDto) {
        return airlineService.save(postAirlineDto);
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
