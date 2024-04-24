package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.RentalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalServiceRepository extends JpaRepository<RentalService,Integer> {
}
