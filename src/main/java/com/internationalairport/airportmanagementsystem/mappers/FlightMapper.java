package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostFlightDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.Airport;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.CheckIn;
import com.internationalairport.airportmanagementsystem.entities.Baggage;
import com.internationalairport.airportmanagementsystem.entities.Feedback;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import com.internationalairport.airportmanagementsystem.entities.FlightSchedule;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.entities.FlightCrew;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightMapper {
    public Flight toFlight(PostFlightDto postFlightDto) {
        Flight flight = new Flight();
        flight.setFlightNumber(postFlightDto.flightNumber());
        flight.setDepartureTime(postFlightDto.departureTime());
        flight.setArrivalTime(postFlightDto.arrivalTime());

        Airport departureAirport = new Airport();
        departureAirport.setAirportId(postFlightDto.departureAirportId());
        flight.setDepartureAirport(departureAirport);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setAirportId(postFlightDto.arrivalAirportId());
        flight.setArrivalAirport(arrivalAirport);

        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftId(postFlightDto.aircraftId());
        flight.setAircraft(aircraft);

        List<CheckIn> checkIns = new ArrayList<>();
        postFlightDto.checkIns().forEach(checkInId -> {
            CheckIn checkIn = new CheckIn();
            checkIn.setCheckInId(checkInId);
            checkIns.add(checkIn);
        });
        flight.setCheckIns(checkIns);

        List<Baggage> baggages = new ArrayList<>();
        postFlightDto.baggages().forEach(baggageId -> {
            Baggage baggage = new Baggage();
            baggage.setBaggageId(baggageId);
            baggages.add(baggage);
        });
        flight.setBaggages(baggages);

        List<Feedback> feedbacks = new ArrayList<>();
        postFlightDto.feedbacks().forEach(feedbackId -> {
            Feedback feedback = new Feedback();
            feedback.setId(feedbackId);
            feedbacks.add(feedback);
        });
        flight.setFeedbacks(feedbacks);

        GateAssignment gateAssignment = new GateAssignment();
        gateAssignment.setAssignmentId(postFlightDto.gateAssignmentId());
        flight.setGateAssignments(gateAssignment);

        List<Ticket> tickets = new ArrayList<>();
        postFlightDto.tickets().forEach(ticketId -> {
            Ticket ticket = new Ticket();
            ticket.setTicketId(ticketId);
            tickets.add(ticket);
        });
        flight.setTickets(tickets);

        List<FlightSchedule> flightSchedules = new ArrayList<>();
        postFlightDto.flightSchedules().forEach(flightScheduleId -> {
            FlightSchedule flightSchedule = new FlightSchedule();
            flightSchedule.setScheduleId(flightScheduleId);
            flightSchedules.add(flightSchedule);
        });
        flight.setFlightSchedules(flightSchedules);

        List<Cargo> cargos = new ArrayList<>();
        postFlightDto.cargos().forEach(cargoId -> {
            Cargo cargo = new Cargo();
            cargo.setCargoId(cargoId);
            cargos.add(cargo);
        });
        flight.setCargos(cargos);

        List<FlightCrew> flightCrews = new ArrayList<>();
        postFlightDto.flightCrews().forEach(flightCrewId -> {
            FlightCrew flightCrew = new FlightCrew();
            flightCrew.setCrewId(flightCrewId);
            flightCrews.add(flightCrew);
        });
        flight.setFlightCrews(flightCrews);

        return flight;
    }
}
