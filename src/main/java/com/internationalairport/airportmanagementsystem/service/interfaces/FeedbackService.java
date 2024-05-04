package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFeedbackDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFeedbackDto;
import com.internationalairport.airportmanagementsystem.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> findAll();
    Feedback findById(int theId);
    Feedback save(PostFeedbackDto postFeedbackDto);
    Feedback save(PutFeedbackDto putFeedbackDto);
    void deleteById(int theId);
}
