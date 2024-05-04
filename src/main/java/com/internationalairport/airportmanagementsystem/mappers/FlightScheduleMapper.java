package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFlightScheduleDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import org.springframework.stereotype.Service;

@Service
public class FlightScheduleMapper {

    public FlightSchedule postToFlightSchedule(PostFlightScheduleDto postFlightScheduleDto) {
        FlightSchedule flightSchedule = new FlightSchedule(
                postFlightScheduleDto.scheduledDepartureTime(),
                postFlightScheduleDto.scheduledArrivalTime(),
                postFlightScheduleDto.status()
        );
        Flight flight = new Flight();
        flight.setFlightId(postFlightScheduleDto.flightId());
        flightSchedule.setFlight(flight);
        return flightSchedule;
    }

    public FlightSchedule putToFlightSchedule(PutFlightScheduleDto putFlightScheduleDto) {
        FlightSchedule flightSchedule = new FlightSchedule(
                putFlightScheduleDto.scheduledDepartureTime(),
                putFlightScheduleDto.scheduledArrivalTime(),
                putFlightScheduleDto.status()
        );
        flightSchedule.setScheduleId(putFlightScheduleDto.scheduleId());
        Flight flight = new Flight();
        flight.setFlightId(putFlightScheduleDto.flightId());
        flightSchedule.setFlight(flight);
        return flightSchedule;
    }
}

