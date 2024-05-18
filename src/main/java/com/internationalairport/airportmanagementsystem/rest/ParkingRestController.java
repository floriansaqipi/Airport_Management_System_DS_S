package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostParkingDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutParkingDto;
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

    @GetMapping("/public/parkings")
    public List<Parking> findAllParkings(){
        return parkingService.findAll();
    }

    @GetMapping("/public/parkings/{parkingId}")
    public Parking getParkingServiceById(@PathVariable int parkingId){
        Parking theParking=parkingService.findById(parkingId);
        if(theParking==null){
            throw new RuntimeException("Id not found - "+parkingId);
        }
        return theParking;
    }

    @PostMapping("/private/parkings")
    public Parking addParking(@RequestBody PostParkingDto postParkingDto){
        return parkingService.save(postParkingDto);
    }

    //add mapping for put
    @PutMapping("/private/parkings")
    public Parking updateParking(@RequestBody PutParkingDto putParkingDto){
        return parkingService.save(putParkingDto);
    }

    @DeleteMapping("/private/parkings/{parkingId}")
    public String deleteParkingById(@PathVariable int parkingId){
        Parking parking = parkingService.findById(parkingId);
        if(parking==null){
            throw new RuntimeException("Id not found - "+parkingId);

        }
        parkingService.deleteById(parkingId);
        return "Deleted Parking id - "+parkingId;
    }
    @DeleteMapping("/private/parkings")
    public String deleteAllParkings() {
        return parkingService.deleteAll();
    }
}
