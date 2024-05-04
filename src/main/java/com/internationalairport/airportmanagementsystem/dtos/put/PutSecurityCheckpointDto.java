package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutSecurityCheckpointDto(
    Integer checkpointId,
    String location,
    String operatingHours
){

}
