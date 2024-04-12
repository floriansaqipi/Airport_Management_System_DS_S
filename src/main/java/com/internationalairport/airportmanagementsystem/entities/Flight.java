package com.internationalairport.airportmanagementsystem.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "flight_number")
    private String flightNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<CheckIn> checkIns;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Baggage> baggages;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Feedback> feedbacks;

    @OneToOne(mappedBy = "flight",
            cascade = CascadeType.ALL)
    private GateAssignment gateAssignments;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<FlightSchedule> flightSchedules;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                       CascadeType.DETACH, CascadeType.REFRESH})
    private List<Cargo> cargos;

    @OneToMany(mappedBy = "flight",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<FlightCrew> flightCrews;

    // Constructors, Getters, and Setters
    public Flight() {
    }

    public Flight(String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void setBaggages(List<Baggage> baggages) {
        this.baggages = baggages;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public GateAssignment getGateAssignments() {
        return gateAssignments;
    }

    public void setGateAssignments(GateAssignment gateAssignments) {
        this.gateAssignments = gateAssignments;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    public void addCheckIn(CheckIn tempCheckIn) {
        if (checkIns == null){
            checkIns = new ArrayList<>();
        }

        checkIns.add(tempCheckIn);

        tempCheckIn.setFlight(this);
    }

    public void addBaggage(Baggage tempBaggage) {
        if (baggages == null){
            baggages = new ArrayList<>();
        }

        baggages.add(tempBaggage);

        tempBaggage.setFlight(this);
    }

    public void addFeedback(Feedback tempFeedback) {
        if (feedbacks == null){
            feedbacks = new ArrayList<>();
        }

        feedbacks.add(tempFeedback);

        tempFeedback.setFlight(this);
    }

    public void addTicket(Ticket tempTicket) {
        if (tickets == null){
            tickets = new ArrayList<>();
        }

        tickets.add(tempTicket);

        tempTicket.setFlight(this);
    }

    public void addFlightSchedule(FlightSchedule tempFlightSchedule) {
        if (flightSchedules == null){
            flightSchedules = new ArrayList<>();
        }

        flightSchedules.add(tempFlightSchedule);

        tempFlightSchedule.setFlight(this);
    }

    public void addCargo(Cargo tempCargo) {
        if (cargos == null){
            cargos = new ArrayList<>();
        }

        cargos.add(tempCargo);

        tempCargo.setFlight(this);
    }

    public void addFlightCrew(FlightCrew tempFlightCrew) {
        if (flightCrews == null){
            flightCrews = new ArrayList<>();
        }

        flightCrews.add(tempFlightCrew);

        tempFlightCrew.setFlight(this);
    }
}

