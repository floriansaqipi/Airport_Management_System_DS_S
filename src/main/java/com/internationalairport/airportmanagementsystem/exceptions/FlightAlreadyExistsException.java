package com.internationalairport.airportmanagementsystem.exceptions;

public class FlightAlreadyExistsException extends RuntimeException{
    public FlightAlreadyExistsException(String message){
        super(message);
    }
}
