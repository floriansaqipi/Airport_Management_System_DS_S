package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.PostBoardingPassDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import org.springframework.stereotype.Service;


@Service
public class BoardingPassMapper {
    public BoardingPass toBoardingPass(PostBoardingPassDto postBoardingPass){
        BoardingPass boardingPass = new BoardingPass(
                postBoardingPass.gate(),
                postBoardingPass.boardingTime()

        );
        Ticket ticket = new Ticket();
        ticket.setTicketId(postBoardingPass.tickedId());

        boardingPass.setTicket(ticket);
        return boardingPass;
    }
}
