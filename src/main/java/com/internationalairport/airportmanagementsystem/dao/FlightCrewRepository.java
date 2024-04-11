package com.internationalairport.airportmanagementsystem.dao;

import com.internationalairport.airportmanagementsystem.entities.FlightCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightCrewRepository extends JpaRepository<FlightCrew, Integer> {
}