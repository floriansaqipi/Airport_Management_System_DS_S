package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftMapper {
    public Aircraft toAircraft(PostAircraftDto postAircraftDto) {
        Aircraft aircraft = new Aircraft();
        aircraft.setTailNumber(postAircraftDto.tailNumber());
        aircraft.setModel(postAircraftDto.model());
        aircraft.setCapacity(postAircraftDto.capacity());

        Airline airline = new Airline();
        airline.setAirlineId(postAircraftDto.airlineId());
        aircraft.setAirline(airline);

        List<Flight> flights = new ArrayList<>();
        postAircraftDto.flights().forEach(flightId -> {
            Flight flight = new Flight();
            flight.setFlightId(flightId);
            flights.add(flight);
        });
        aircraft.setFlights(flights);

        List<Maintenance> maintenances = new ArrayList<>();
        postAircraftDto.maintenances().forEach(maintenanceId -> {
            Maintenance maintenance = new Maintenance();
            maintenance.setId(maintenanceId);
            maintenances.add(maintenance);
        });
        aircraft.setMaintenances(maintenances);

        return aircraft;
    }
}
