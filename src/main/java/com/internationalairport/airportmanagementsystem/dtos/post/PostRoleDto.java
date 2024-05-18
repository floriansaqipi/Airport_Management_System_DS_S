package com.internationalairport.airportmanagementsystem.dtos.post;

import java.util.List;

public record PostRoleDto(
        String roleName,

        List<Integer> abilityIds
) {
}
