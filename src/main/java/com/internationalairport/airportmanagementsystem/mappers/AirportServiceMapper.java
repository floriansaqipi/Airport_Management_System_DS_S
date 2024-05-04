package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAirportServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAirportServiceDto;
import com.internationalairport.airportmanagementsystem.entities.AirportService;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceMapper {
    public AirportService postToAirportService(PostAirportServiceDto postAirportServiceDto) {
        AirportService AirportService = new AirportService(
                postAirportServiceDto.name(),
                postAirportServiceDto.location(),
                postAirportServiceDto.operatingHours()
        );
        AirportService.setServiceId(0);

        return AirportService;
    }

    public AirportService putToAirportService(PutAirportServiceDto putAirportServiceDto) {
        AirportService AirportService = new AirportService(
                putAirportServiceDto.name(),
                putAirportServiceDto.location(),
                putAirportServiceDto.operatingHours()
        );
        AirportService.setServiceId(putAirportServiceDto.serviceId());

        return AirportService;
    }
}