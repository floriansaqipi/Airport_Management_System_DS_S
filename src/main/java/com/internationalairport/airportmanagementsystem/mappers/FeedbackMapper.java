package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFeedbackDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutFeedbackDto;
import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
    public Feedback postToFeedback(PostFeedbackDto postFeedbackDto) {
        Feedback feedback = new Feedback(
               postFeedbackDto.content(),
                postFeedbackDto.status()
        );
        feedback.setFeedbackId(0);

        if(postFeedbackDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setPassengerId(postFeedbackDto.passengerId());
            feedback.setPassenger(passenger);
        }
        if(postFeedbackDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(postFeedbackDto.flightId());
            feedback.setFlight(flight);
        }

        return feedback;
    }

    public Feedback putToFeedback(PutFeedbackDto putFeedbackDto) {
        Feedback feedback = new Feedback(
                putFeedbackDto.content(),
                putFeedbackDto.status()
        );
        feedback.setFeedbackId(putFeedbackDto.feedbackId());

        if(putFeedbackDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setPassengerId(putFeedbackDto.passengerId());
            feedback.setPassenger(passenger);
        }

        if(putFeedbackDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(putFeedbackDto.flightId());
            feedback.setFlight(flight);
        }
        return feedback;
    }
}
