package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonIgnoreProperties({"baggages", "feedbacks", "checkIns", "tickets", "userEntity"})
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargos", "employees"})
    private Flight flight;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    // Constructors, getters, and setters
    public Feedback() {
    }

    public Feedback( String content, String status) {
        this.content = content;
        this.status = status;
    }

    // Getters and setters
    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
