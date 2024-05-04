package com.internationalairport.airportmanagementsystem.dtos.post;
import java.math.BigDecimal;

public record PostParkingDto(
    String location,
    Integer capacity,
    BigDecimal rate
){

}
