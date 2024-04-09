package com.internationalairport.airportmanagementsystem.dao;

import com.internationalairport.airportmanagementsystem.entities.AirportService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportServiceRepository extends JpaRepository<AirportService,Integer> {
}
