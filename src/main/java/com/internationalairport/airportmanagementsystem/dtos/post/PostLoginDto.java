package com.internationalairport.airportmanagementsystem.dtos.post;

import lombok.Data;

@Data
public class PostLoginDto {
    private String username;
    private String password;
}