package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> findAll();

    Feedback findById(int theId);

    Feedback save(Feedback theFeedback);

    void deleteById(int theId);
}
