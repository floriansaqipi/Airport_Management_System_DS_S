package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking,Integer> {
}
