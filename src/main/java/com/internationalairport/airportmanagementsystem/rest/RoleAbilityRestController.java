package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleAbilityDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleAbilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private")
public class RoleAbilityRestController {

    private RoleAbilityService roleAbilityService;

    @Autowired
    public RoleAbilityRestController(RoleAbilityService roleAbilityService) {
        this.roleAbilityService = roleAbilityService;
    }


    @Operation(
            description = "Endpoint to add a new role ability",
            summary = "Add a new role ability",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the role ability",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/role_abilities")
    public Role addRoleAbility(@RequestBody PostRoleAbilityDto postRoleAbilityDto) {
        return roleAbilityService.save(postRoleAbilityDto);
    }

    @Operation(
            description = "Endpoint to delete a role ability by ID",
            summary = "Delete a role ability by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the role ability",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Role ability ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/role_abilities/{roleId}/{abilityId}")
    public String deleteRoleAbilityById(@PathVariable int roleId, @PathVariable int abilityId) {
        roleAbilityService.deleteByRoleIdAndAbilityId(roleId,abilityId);
        return "Deleted role ability with id - " +  roleId + "-" + abilityId;
    }
}
