package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateAssignmentRepository extends JpaRepository<GateAssignment, Integer> {
}
