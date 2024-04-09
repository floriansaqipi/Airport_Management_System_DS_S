package com.internationalairport.airportmanagementsystem.dao;

import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityCheckpointRepository extends JpaRepository<SecurityCheckpoint,Integer> {
}
