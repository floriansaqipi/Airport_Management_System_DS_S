package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftMapper {
    public Aircraft postToAircraft(PostAircraftDto postAircraftDto) {
        Aircraft aircraft = new Aircraft(
                postAircraftDto.tailNumber(),
                postAircraftDto.model(),
                postAircraftDto.capacity()
        );
      aircraft.setAircraftId(0);

      if(postAircraftDto.airlineId() != null){
          Airline airline = new Airline();
          airline.setAirlineId(postAircraftDto.airlineId());
          aircraft.setAirline(airline);
      }

      return aircraft;
    }

    public Aircraft putToAircraft(PutAircraftDto putAircraftDto) {
        Aircraft aircraft = new Aircraft(
                putAircraftDto.tailNumber(),
                putAircraftDto.model(),
                putAircraftDto.capacity()
        );
        aircraft.setAircraftId(putAircraftDto.aircraftId());

        if(putAircraftDto.airlineId() != null){
            Airline airline = new Airline();
            airline.setAirlineId(putAircraftDto.airlineId());
            aircraft.setAirline(airline);
        }

        return aircraft;
    }
}
