package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFeedbackDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFeedbackDto;
import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairport.airportmanagementsystem.service.interfaces.FeedbackService;
import com.internationalairport.airportmanagementsystem.service.interfaces.PassengerService;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackRestController {
    private FeedbackService feedbackService;

    private UserEntityService userEntityService;

    private PassengerService passengerService;

    @Autowired
    public FeedbackRestController(FeedbackService theFeedbackService, PassengerService thePassengerService, UserEntityService theuserEntityService) {
        feedbackService = theFeedbackService;
        passengerService = thePassengerService;
        userEntityService = theuserEntityService;
    }
    @GetMapping("/public/feedbacks")
    public List<Feedback> findAll() {
        return feedbackService.findAll();
    }
    @GetMapping("/public/feedbacks/{feedbackId}")
    public Feedback getFeedback(@PathVariable int feedbackId) {
        Feedback theFeedback = feedbackService.findById(feedbackId);
        if (theFeedback == null) {
            throw new RuntimeException("Feedback id not found - " + feedbackId);
        }
        return theFeedback;
    }
    @PostMapping("/private/feedbacks")
    public Feedback addFeedback(@RequestBody PostFeedbackDto postFeedbackDto) {
        return feedbackService.save(postFeedbackDto);
    }
    @PutMapping("/private/feedbacks")
    public Feedback updateFeedback(@RequestBody PutFeedbackDto putFeedbackDto) {
        Feedback tempFeedback = feedbackService.findById(putFeedbackDto.feedbackId());
        if(tempFeedback == null){
            throw new RuntimeException("Baggage id not found - " + tempFeedback);
        }

        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, tempFeedback);
        Feedback dbFeedback = feedbackService.save(putFeedbackDto);
        return dbFeedback;
    }
    @DeleteMapping("/private/feedbacks/{feedbackId}")
    public String deleteFeedback(@PathVariable int feedbackId) {
        Feedback tempFeedback = feedbackService.findById(feedbackId);
        if (tempFeedback == null) {
            throw new RuntimeException("Feedback id not found - " + feedbackId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, tempFeedback);
        feedbackService.deleteById(feedbackId);

        return "Deleted feedback id - " + feedbackId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }

    private void authorizeAccess(UserEntity user, Feedback feedback) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != feedback.getPassenger().getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}