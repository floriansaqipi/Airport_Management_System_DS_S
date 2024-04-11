package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.service.interfaces.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BaggageRestController{

    private BaggageService baggageService;

    @Autowired
    public BaggageRestController(BaggageService theBaggageService){
        baggageService = theBaggageService;
    }

    @GetMapping("/baggage")
    public List<Baggage> findAll(){
        return baggageService.findAll();
    }

    @GetMapping("/baggage/{baggageId}")
    public Baggage getBaggage(@PathVariable int baggageId){
        Baggage theBaggage = baggageService.findById(baggageId);
        if(theBaggage == null){
            throw new RuntimeException("Cargo id not found - " + theBaggage);
        }
        return theBaggage;
    }

    @PostMapping("/baggage")
    public Baggage addBaggage(@RequestBody Baggage theBaggage){
        theBaggage.setBaggageId(0);
        Baggage baggage = baggageService.save(theBaggage);
        return baggage;
    }

    @PutMapping("/baggage")
    public Baggage updateBaggage(@RequestBody Baggage theBaggage){
        Baggage dbBaggage = baggageService.save(theBaggage);
        return dbBaggage;
    }

    @DeleteMapping("/baggage/{baggageId}")
    public String deleteBaggage(@PathVariable int baggageId){
        Baggage tempBaggage = baggageService.findById(baggageId);
        if(tempBaggage == null){
            throw new RuntimeException("Cargo id not found - " + baggageId);
        }
        baggageService.deleteById(baggageId);
        return "Deleted Cargo id - " + baggageId;
    }

}
