package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutRentalServiceDto(
    Integer rentalId,
    String type,
    String description,
    Double rate
){

}
