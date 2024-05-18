package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportServiceDto;
import com.internationalairport.airportmanagementsystem.entities.AirportService;
import com.internationalairport.airportmanagementsystem.service.interfaces.AirportServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportServiceRestController {

    private AirportServiceService airportServiceService;

    @Autowired
    public AirportServiceRestController(AirportServiceService theAirportServiceService){
        airportServiceService=theAirportServiceService;
    }

    @GetMapping("/public/airport_services")
    public List<AirportService> findAllAirportServices(){
        return airportServiceService.findAll();
    }

    @GetMapping("/public/airport_services/{airportServiceId}")
    public AirportService getAirportServiceById(@PathVariable int airportServiceId){
        AirportService theAirportService=airportServiceService.findById(airportServiceId);
        if(theAirportService==null){
            throw new RuntimeException("Id not found - "+airportServiceId);
        }
        return theAirportService;
    }

    @PostMapping("/private/airport_services")
    public AirportService addAirportService(@RequestBody PostAirportServiceDto postAirportServiceDto){
        return airportServiceService.save(postAirportServiceDto);
    }

    @PutMapping("/private/airport_services")
    public AirportService updateAirportService(@RequestBody PutAirportServiceDto putAirportServiceDto){
        return airportServiceService.save(putAirportServiceDto);
    }

    @DeleteMapping("/private/airport_services/{airportServiceId}")
    public String deleteAirportServiceById(@PathVariable int airportServiceId){
        AirportService tempAirportService = airportServiceService.findById(airportServiceId);
        if(tempAirportService==null){
            throw new RuntimeException("Id not found - "+airportServiceId);

        }
        airportServiceService.deleteById(airportServiceId);
        return "Deleted Airport Service id - "+airportServiceId;
    }
    @DeleteMapping("/private/airport_services")
    public String deleteAllAirportServices() {
        return airportServiceService.deleteAll();
    }
}
