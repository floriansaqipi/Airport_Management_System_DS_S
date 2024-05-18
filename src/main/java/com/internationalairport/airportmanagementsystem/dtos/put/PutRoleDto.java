package com.internationalairport.airportmanagementsystem.dtos.put;

import java.util.List;

public record PutRoleDto(
        Integer roleId,
        String roleName,

        List<Integer> abilityIds
) {
}
