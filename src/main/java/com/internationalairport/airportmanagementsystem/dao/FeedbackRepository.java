package com.internationalairport.airportmanagementsystem.dao;

import com.internationalairport.airportmanagementsystem.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
}
