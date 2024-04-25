package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackRestController {
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackRestController(FeedbackService theFeedbackService) {
        feedbackService = theFeedbackService;
    }

    @GetMapping("/feedbacks")
    public List<Feedback> findAll() {
        return feedbackService.findAll();
    }

    @GetMapping("/feedbacks/{feedbackId}")
    public Feedback getFeedback(@PathVariable int feedbackId) {

        Feedback theFeedback = feedbackService.findById(feedbackId);

        if (theFeedback == null) {
            throw new RuntimeException("Feedback id not found - " + feedbackId);
        }

        return theFeedback;
    }

    @PostMapping("/feedbacks")
    public Feedback addFeedback(@RequestBody Feedback theFeedback) {
        theFeedback.setId(0);
        Feedback dbFeedback = feedbackService.save(theFeedback);
        return dbFeedback;
    }

    @PutMapping("/feedbacks")
    public Feedback updateFeedback(@RequestBody Feedback theFeedback) {
        Feedback dbFeedback = feedbackService.save(theFeedback);
        return dbFeedback;
    }

    @DeleteMapping("/feedbacks/{feedbackId}")
    public String deleteFeedback(@PathVariable int feedbackId) {
        Feedback tempFeedback = feedbackService.findById(feedbackId);
        if (tempFeedback == null) {
            throw new RuntimeException("Feedback id not found - " + feedbackId);
        }
        feedbackService.deleteById(feedbackId);
        return "Deleted feedback id - " + feedbackId;
    }

}
