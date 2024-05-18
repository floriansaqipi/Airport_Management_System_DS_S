package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostBoardingPassDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutBoardingPassDto;
import com.internationalairport.airportmanagementsystem.entities.BoardingPass;
import com.internationalairport.airportmanagementsystem.entities.Ticket;
import org.springframework.stereotype.Service;


@Service
public class BoardingPassMapper {
    public BoardingPass postToBoardingPass(PostBoardingPassDto postBoardingPass){
        BoardingPass boardingPass = new BoardingPass(
                postBoardingPass.gate(),
                postBoardingPass.boardingTime()

        );
        boardingPass.setBoardingPassId(0);

        if(postBoardingPass.ticketId() != null){
            Ticket ticket = new Ticket();
            ticket.setTicketId(postBoardingPass.ticketId());
            boardingPass.setTicket(ticket);
        }
        return boardingPass;
    }

    public BoardingPass putToBoardingPass(PutBoardingPassDto putBoardingPassDto){
        BoardingPass boardingPass = new BoardingPass(
                putBoardingPassDto.gate(),
                putBoardingPassDto.boardingTime()

        );
        boardingPass.setBoardingPassId(putBoardingPassDto.boardingPassId());

        if(putBoardingPassDto.ticketId() != null){
            Ticket ticket = new Ticket();
            ticket.setTicketId(putBoardingPassDto.ticketId());
            boardingPass.setTicket(ticket);
        }
        return boardingPass;
    }
}
