package com.internationalairport.airportmanagementsystem.dtos.put;

import java.util.List;

public record PutUserDto(
        Integer userId,
        String username,
        String password,
        List<Integer> roleIds
) {
}
