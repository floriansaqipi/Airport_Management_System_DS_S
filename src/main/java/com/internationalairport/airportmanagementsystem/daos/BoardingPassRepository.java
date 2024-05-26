package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardingPassRepository extends JpaRepository<BoardingPass, Integer> {

    @Query("SELECT b FROM BoardingPass b JOIN FETCH b.ticket t JOIN FETCH t.passenger p WHERE p.passengerId = :passengerId")
    List<BoardingPass> findByPassengerId(@Param("passengerId") Integer passengerId);

}
