package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    @Query("SELECT c FROM CheckIn c JOIN FETCH c.passenger p WHERE p.passengerId = :passengerId")
    List<CheckIn> findByPassengerId(@Param("passengerId") Integer passengerId);

}
