package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutGateAssignmentDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.GateAssignment;
import org.springframework.stereotype.Service;

@Service
public class GateAssignmentMapper {

    public GateAssignment postToGateAssignment(PostGateAssignmentDto postGateAssignmentDto){
        GateAssignment gateAssignment = new GateAssignment(
                postGateAssignmentDto.gate(),
                postGateAssignmentDto.assignmentTime()
        );
        Flight flight = new Flight();
        flight.setFlightId(postGateAssignmentDto.flightId());
        gateAssignment.setFlight(flight);
        return gateAssignment;
    }

    public GateAssignment putToGateAssignment(PutGateAssignmentDto putGateAssignmentDto){
        GateAssignment gateAssignment = new GateAssignment(
                putGateAssignmentDto.gate(),
                putGateAssignmentDto.assignmentTime()
        );
        gateAssignment.setAssignmentId(putGateAssignmentDto.assignmentId());
        Flight flight = new Flight();
        flight.setFlightId(putGateAssignmentDto.flightId());
        gateAssignment.setFlight(flight);
        return gateAssignment;
    }
}

