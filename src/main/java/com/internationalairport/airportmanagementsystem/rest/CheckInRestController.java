package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.service.interfaces.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CheckInRestController {

    private CheckInService checkInService;

    @Autowired
    public CheckInRestController(CheckInService theCheckInService){
        checkInService = theCheckInService;
    }

    @GetMapping("/check_ins")
    public List<CheckIn> findAllCheckIns(){
        return checkInService.findAll();
    }

    @GetMapping("/check_ins/{checkInId}")
    public CheckIn getCheckInById(@PathVariable int checkInId){
        CheckIn theCheckIn = checkInService.findById(checkInId);
        if(theCheckIn == null){
            throw new RuntimeException("Id not found - " + checkInId);
        }
        return theCheckIn;
    }

    @PostMapping("/check_ins")
    public CheckIn addCheckIn(@RequestBody CheckIn theCheckIn){
        theCheckIn.setCheckInId(0);
        CheckIn dbCheckIn = checkInService.save(theCheckIn);
        return dbCheckIn;
    }

    @PutMapping("/check_ins")
    public CheckIn updateCheckIn(@RequestBody CheckIn theCheckIn){
        CheckIn checkIn = checkInService.save(theCheckIn);
        return checkIn;
    }

    @DeleteMapping("/check_ins/{checkInId}")
    public String deleteCheckInById(@PathVariable int checkInId){
        CheckIn tempCheckIn = checkInService.findById(checkInId);
        if(tempCheckIn == null){
            throw new RuntimeException("Id not found - " + checkInId);
        }
        checkInService.deleteById(checkInId);
        return "Deleted id - " + checkInId;
    }

    @DeleteMapping("/check_ins")
    public String deleteAllCheckIns() {
        return checkInService.deleteAll();
    }
}

