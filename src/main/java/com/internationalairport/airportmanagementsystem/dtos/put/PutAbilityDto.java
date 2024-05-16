package com.internationalairport.airportmanagementsystem.dtos.put;

public record PutAbilityDto(
        Integer abilityId,
        String entity,
        String verb,
        String field
) {
}
