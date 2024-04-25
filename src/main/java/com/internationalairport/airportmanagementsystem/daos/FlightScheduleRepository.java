package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {
}
