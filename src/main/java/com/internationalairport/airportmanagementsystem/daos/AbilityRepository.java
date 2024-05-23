package com.internationalairport.airportmanagementsystem.daos;

import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    @Query("SELECT a FROM Ability a JOIN FETCH a.roles r WHERE r.roleId = :roleId")
    List<Ability> findByRoleId(@Param("roleId") Integer roleId);
}
