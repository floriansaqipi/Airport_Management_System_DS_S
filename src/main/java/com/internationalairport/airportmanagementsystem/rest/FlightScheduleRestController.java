package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.service.interfaces.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightScheduleRestController {

    private FlightScheduleService flightScheduleService;

    @Autowired
    public FlightScheduleRestController(FlightScheduleService theFlightScheduleService){
        flightScheduleService = theFlightScheduleService;
    }

    @GetMapping("/flight_schedules")
    public List<FlightSchedule> findAllFlightSchedules(){
        return flightScheduleService.findAll();
    }

    @GetMapping("/flight_schedules/{flightScheduleId}")
    public FlightSchedule getFlightScheduleById(@PathVariable int flightScheduleId){
        FlightSchedule theFlightSchedule = flightScheduleService.findById(flightScheduleId);
        if(theFlightSchedule == null){
            throw new RuntimeException("Flight schedule not found - "+flightScheduleId);
        }
        return theFlightSchedule;
    }

    @PostMapping("/flight_schedules")
    public FlightSchedule addFlightSchedule(@RequestBody FlightSchedule theFlightSchedule){
        theFlightSchedule.setScheduleId(0);
        return flightScheduleService.save(theFlightSchedule);
    }

    @PutMapping("/flight_schedules")
    public FlightSchedule updateFlightSchedule(@RequestBody FlightSchedule theFlightSchedule){
        return flightScheduleService.save(theFlightSchedule);
    }

    @DeleteMapping("/flight_schedules/{flightScheduleId}")
    public String deleteFlightScheduleById(@PathVariable int flightScheduleId){
        FlightSchedule tempFlightSchedule = flightScheduleService.findById(flightScheduleId);
        if(tempFlightSchedule == null){
            throw new RuntimeException("Flight schedule not found - "+flightScheduleId);
        }
        flightScheduleService.deleteById(flightScheduleId);
        return "Deleted flight schedule id - "+flightScheduleId;
    }

    @DeleteMapping("/flight_schedules")
    public String deleteAllFlightSchedules() {
        return flightScheduleService.deleteAll();
    }
}

