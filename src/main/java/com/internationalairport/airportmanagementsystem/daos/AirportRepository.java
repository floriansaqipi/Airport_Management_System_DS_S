package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
