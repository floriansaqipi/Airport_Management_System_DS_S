package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutAirportServiceDto(
    Integer serviceId,
    String name,
    String location,
    String operatingHours
){

}
