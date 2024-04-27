package com.internationalairport.airportmanagementsystem.daos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightCrewRepository extends JpaRepository<FlightCrew, Integer> {
}