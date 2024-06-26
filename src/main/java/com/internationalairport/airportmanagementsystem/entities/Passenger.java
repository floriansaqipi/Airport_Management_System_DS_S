package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    @Column(name = "name")
    private String name;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "contact_details")
    private String contactDetails;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<CheckIn> checkIns;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger", "boardingPass"})
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<Baggage> baggages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_user_id")
    @JsonIgnoreProperties({"role", "passenger", "employee"})
    private UserEntity userEntity;

    @PreRemove
    public void preRemove(){
        for(Feedback feedback : feedbacks) {
            feedback.setPassenger(null);
        }
        for(CheckIn checkIn : checkIns) {
            checkIn.setPassenger(null);
        }
        for(Ticket ticket : tickets) {
            ticket.setPassenger(null);
        }
        for(Baggage baggage : baggages) {
            baggage.setPassenger(null);
        }
    }


    // Constructors, getters, and setters
    public Passenger() {
    }

    public Passenger(String name, String passportNumber, String nationality, String contactDetails) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.contactDetails = contactDetails;
    }

    // Getters and setters
    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", feedbacks=" + feedbacks +
                ", checkIns=" + checkIns +
                ", tickets=" + tickets +
                ", baggages=" + baggages +
                '}';
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void setBaggages(List<Baggage> baggages) {
        this.baggages = baggages;
    }


    // define convenient methods

    public void addFeedback(Feedback tempFeedback) {
        if( feedbacks == null) {
            feedbacks = new ArrayList<>();
        }

        feedbacks.add(tempFeedback);

        tempFeedback.setPassenger(this);
    }

    public void addTicket(Ticket tempTicket) {
        if (tickets == null) {
            tickets = new ArrayList<>();
        }

        tickets.add(tempTicket);

        tempTicket.setPassenger(this);
    }

    public void addBaggage(Baggage tempBaggage) {
        if (baggages == null) {
            baggages = new ArrayList<>();
        }

        baggages.add(tempBaggage);

        tempBaggage.setPassenger(this);
    }

    public void addCheckIn(CheckIn tempCheckIn) {
        if (checkIns == null) {
            checkIns = new ArrayList<>();
        }

        checkIns.add(tempCheckIn);

        tempCheckIn.setPassenger(this);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
