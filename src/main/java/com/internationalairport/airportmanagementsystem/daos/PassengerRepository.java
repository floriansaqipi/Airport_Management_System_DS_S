package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {

    @Query("SELECT p FROM Passenger p JOIN FETCH p.userEntity u WHERE u.userId = :userId")
    Optional<Passenger> findByUserEntityId(@Param("userId") Integer userId);
}
