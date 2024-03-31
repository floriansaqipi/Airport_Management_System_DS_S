package com.internationalairport.airportmanagementsystem.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportRestController {
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/flights")
    public String getFlights(){
        return "This is a list of the available flights";
    }

}
