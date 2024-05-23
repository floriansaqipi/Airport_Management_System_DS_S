package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.FeedbackRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostFeedbackDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFeedbackDto;
import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.mappers.FeedbackMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;
    private FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository theFeedbackRepository, FeedbackMapper theFeedbackMapper) {
        feedbackRepository = theFeedbackRepository;
        feedbackMapper = theFeedbackMapper;
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
    public Feedback save(PutFeedbackDto putFeedbackDto) {
        Feedback feedback = feedbackMapper.putToFeedback(putFeedbackDto);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback save(PostFeedbackDto postFeedbackDto) {
        Feedback feedback = feedbackMapper.postToFeedback(postFeedbackDto);
        return feedbackRepository.save(feedback);
    }


    @Override
    public void deleteById(int theId) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(theId);
        if (feedbackOptional.isPresent()) {
            feedbackRepository.deleteById(theId);
        } else {
            throw new RuntimeException("Feedback id not found - " + theId);
        }
    }
}
