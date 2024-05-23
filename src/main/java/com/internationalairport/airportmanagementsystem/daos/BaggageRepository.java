package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
    @Query("SELECT b FROM Baggage b JOIN FETCH b.passenger p WHERE p.passengerId = :passengerId")
    List<Baggage> findByPassengerId(@Param("passengerId") Integer passengerId);

}
