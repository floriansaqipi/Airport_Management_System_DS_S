package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.PostAirportDto;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportRestController {

    private AirportService airportService;

    @Autowired
    public AirportRestController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public List<Airport> findAll() {
        return airportService.findAll();
    }

    @GetMapping("/airports/{airportId}")
    public Airport getAirportById(@PathVariable int airportId) {
        Airport airport = airportService.findById(airportId);
        if (airport == null) {
            throw new RuntimeException("Airport not found for id - " + airportId);
        }
        return airport;
    }

    @PostMapping("/airports")
    public Airport addAirport(@RequestBody PostAirportDto postAirportDto) {
        return airportService.save(postAirportDto);
    }

    @PutMapping("/airports")
    public Airport updateAirport(@RequestBody PostAirportDto postAirportDto) {
        return airportService.save(postAirportDto);
    }

    @DeleteMapping("/airports/{airportId}")
    public String deleteAirportById(@PathVariable int airportId) {
        Airport airport = airportService.findById(airportId);
        if (airport == null) {
            throw new RuntimeException("Airport not found for id - " + airportId);
        }
        airportService.deleteById(airportId);
        return "Deleted airport with id - " + airportId;
    }

    @DeleteMapping("/airports")
    public String deleteAllAirports() {
        return airportService.deleteAll();
    }
}
