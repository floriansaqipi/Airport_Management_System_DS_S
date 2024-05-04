package com.internationalairport.airportmanagementsystem.dtos.put;

import java.math.BigDecimal;

public record PutParkingDto(
    Integer parkingId,
    String location,
    Integer capacity,
    BigDecimal rate
){

}
