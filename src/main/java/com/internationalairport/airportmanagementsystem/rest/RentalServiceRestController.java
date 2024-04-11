package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.RentalService;
import com.internationalairport.airportmanagementsystem.service.interfaces.RentalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalServiceRestController {

    private RentalServiceService rentalServiceService;

    @Autowired
    public RentalServiceRestController(RentalServiceService theRentalService){
        rentalServiceService=theRentalService;
    }

    @GetMapping("/rental_services")
    public List<RentalService> findAllRentalServices(){
        return rentalServiceService.findAll();
    }

    @GetMapping("/rental_services/{rentalServiceId}")
    public RentalService getRentalServiceById(@PathVariable int rentalServiceId){
        RentalService theRentalService=rentalServiceService.findById(rentalServiceId);
        if(theRentalService==null){
            throw new RuntimeException("Id not found - "+rentalServiceId);
        }
        return theRentalService;
    }

    @PostMapping("/rental_services")
    public RentalService addRentalService(@RequestBody RentalService theRentalService){
        theRentalService.setRentalId(0);
        RentalService rentalService=rentalServiceService.save(theRentalService);
        return rentalService;
    }

    @PutMapping("/rental_services")
    public RentalService updateRentalService(@RequestBody RentalService theRentalService){
        RentalService rentalService = rentalServiceService.save(theRentalService);
        return rentalService;
    }

    @DeleteMapping("/rental_services/{rentalServiceId}")
    public String deleteRentalServiceById(@PathVariable int rentalServiceId){
        RentalService rentalService = rentalServiceService.findById(rentalServiceId);
        if(rentalService==null){
            throw new RuntimeException("Id not found - "+rentalServiceId);

        }
        rentalServiceService.deleteById(rentalServiceId);
        return "Deleted  id - "+rentalServiceId;
    }
    @DeleteMapping("/rental_services")
    public String deleteAllRentalServices() {
        return rentalServiceService.deleteAll();
    }
}