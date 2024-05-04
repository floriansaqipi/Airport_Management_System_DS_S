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

    @GetMapping("/airport_services")
    public List<AirportService> findAllAirportServices(){
        return airportServiceService.findAll();
    }

    @GetMapping("/airport_services/{airportServiceId}")
    public AirportService getAirportServiceById(@PathVariable int airportServiceId){
        AirportService theAirportService=airportServiceService.findById(airportServiceId);
        if(theAirportService==null){
            throw new RuntimeException("Id not found - "+airportServiceId);
        }
        return theAirportService;
    }

    @PostMapping("/airport_services")
    public AirportService addAirportService(@RequestBody PostAirportServiceDto postAirportServiceDto){
        return airportServiceService.save(postAirportServiceDto);
    }

    @PutMapping("/airport_services")
    public AirportService updateAirportService(@RequestBody PutAirportServiceDto putAirportServiceDto){
        return airportServiceService.save(putAirportServiceDto);
    }

    @DeleteMapping("/airport_services/{airportServiceId}")
    public String deleteAirportServiceById(@PathVariable int airportServiceId){
        AirportService tempAirportService = airportServiceService.findById(airportServiceId);
        if(tempAirportService==null){
            throw new RuntimeException("Id not found - "+airportServiceId);

        }
        airportServiceService.deleteById(airportServiceId);
        return "Deleted  id - "+airportServiceId;
    }
    @DeleteMapping("/airport_services")
    public String deleteAllAirportServices() {
        return airportServiceService.deleteAll();
    }
}
