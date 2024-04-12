package com.internationalairport.airportmanagementsystem.rest;

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
    public AirportService addAirportService(@RequestBody AirportService theAirportService){
        theAirportService.setServiceId(0);
        AirportService dbAirportService = airportServiceService.save(theAirportService);
        return dbAirportService;
    }

    @PutMapping("/airport_services")
    public AirportService updateAirportService(@RequestBody AirportService theAirportService){
        AirportService airportService = airportServiceService.save(theAirportService);
        return airportService;
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
