package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.FeedbackRepository;
import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository theFeedbackRepository) {
        feedbackRepository = theFeedbackRepository;
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(int theId) {
        Optional<Feedback> result = feedbackRepository.findById(theId);

        Feedback theFeedback = null;

        if (result.isPresent()) {
            theFeedback = result.get();
        }
        else {
            // we didn't find the feedback
            throw new RuntimeException("Did not find feedback id - " + theId);
        }

        return theFeedback;
    }

    @Override
    public Feedback save(Feedback theFeedback) {
        return feedbackRepository.save(theFeedback);
    }

    @Override
    public void deleteById(int theId) {
        feedbackRepository.deleteById(theId);
    }

}
