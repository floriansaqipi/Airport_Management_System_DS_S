package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRentalServiceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRentalServiceDto;
import com.internationalairport.airportmanagementsystem.entities.RentalService;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceMapper {
    public RentalService postToRentalServiceMapper(PostRentalServiceDto postRentalServiceDto) {
        RentalService RentalService = new RentalService(
                postRentalServiceDto.type(),
                postRentalServiceDto.description(),
                postRentalServiceDto.rate()
        );
        RentalService.setRentalId(0);

        return RentalService;
    }

    public RentalService putToRentalService(PutRentalServiceDto putRentalServiceDto) {
        RentalService RentalService = new RentalService(
                putRentalServiceDto.type(),
                putRentalServiceDto.description(),
                putRentalServiceDto.rate()
        );
        RentalService.setRentalId(putRentalServiceDto.rentalId());

        return RentalService;
    }
}