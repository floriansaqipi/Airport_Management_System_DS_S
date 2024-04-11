package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.Parking;
import com.internationalairport.airportmanagementsystem.service.interfaces.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingRestController {

    private ParkingService parkingService;

    @Autowired
    public ParkingRestController(ParkingService theParkingService){
        parkingService=theParkingService;
    }

    @GetMapping("/parkings")
    public List<Parking> findAllParkings(){
        return parkingService.findAll();
    }

    @GetMapping("/parkings/{parkingId}")
    public Parking getParkingServiceById(@PathVariable int parkingId){
        Parking theParking=parkingService.findById(parkingId);
        if(theParking==null){
            throw new RuntimeException("Id not found - "+parkingId);
        }
        return theParking;
    }

    @PostMapping("/parkings")
    public Parking addParking(@RequestBody Parking theParking){
        theParking.setParkingId(0);
        Parking parking= parkingService.save(theParking);
        return parking;
    }

    //add mapping for put
    @PutMapping("/parkings")
    public Parking updateParking(@RequestBody Parking theParking){
        Parking parking = parkingService.save(theParking);
        return parking;
    }

    @DeleteMapping("/parkings/{parkingId}")
    public String deleteParkingById(@PathVariable int parkingId){
        Parking parking = parkingService.findById(parkingId);
        if(parking==null){
            throw new RuntimeException("Id not found - "+parkingId);

        }
        parkingService.deleteById(parkingId);
        return "Deleted id - "+parkingId;
    }
    @DeleteMapping("/parkings")
    public String deleteAllParkings() {
        return parkingService.deleteAll();
    }
}
