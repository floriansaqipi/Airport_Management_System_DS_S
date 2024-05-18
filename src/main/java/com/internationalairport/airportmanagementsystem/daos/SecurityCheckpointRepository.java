package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityCheckpointRepository extends JpaRepository<SecurityCheckpoint,Integer> {
}
