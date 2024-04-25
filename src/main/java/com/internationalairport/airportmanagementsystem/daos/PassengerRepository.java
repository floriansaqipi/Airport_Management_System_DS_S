package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
}
