package com.internationalairport.airportmanagementsystem.dao;

import com.internationalairport.airportmanagementsystem.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
