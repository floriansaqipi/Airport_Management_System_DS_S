package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightScheduleRestController {

    private final FlightScheduleService flightScheduleService;

    @Autowired
    public FlightScheduleRestController(FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    @GetMapping("/public/flight_schedules")
    public List<FlightSchedule> findAllFlightSchedules() {
        return flightScheduleService.findAll();
    }

    @GetMapping("/public/flight_schedules/{flightScheduleId}")
    public FlightSchedule getFlightScheduleById(@PathVariable int flightScheduleId) {
        return flightScheduleService.findById(flightScheduleId);
    }

    @PostMapping("/private/flight_schedules")
    public FlightSchedule addFlightSchedule(@RequestBody PostFlightScheduleDto postFlightScheduleDto) {
        return flightScheduleService.save(postFlightScheduleDto);
    }

    @PutMapping("/private/flight_schedules")
    public FlightSchedule updateFlightSchedule(@RequestBody PutFlightScheduleDto putFlightScheduleDto) {
        return flightScheduleService.save(putFlightScheduleDto);
    }

    @DeleteMapping("/private/flight_schedules/{flightScheduleId}")
    public String deleteFlightScheduleById(@PathVariable int flightScheduleId) {
        flightScheduleService.deleteById(flightScheduleId);
        return "Deleted flight schedule id - " + flightScheduleId;
    }

    @DeleteMapping("/private/flight_schedules")
    public String deleteAllFlightSchedules() {
        return flightScheduleService.deleteAll();
    }
}
