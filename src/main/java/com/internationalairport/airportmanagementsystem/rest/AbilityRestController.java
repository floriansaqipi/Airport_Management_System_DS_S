package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.service.interfaces.AbilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class AbilityRestController {

    private AbilityService abilityService;

    @Autowired
    public AbilityRestController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @Operation(
            description = "Get endpoint to retrieve all abilities that a user can have. This includes abilities such as specific permissions or skills that can be assigned to users.",
            summary = "Retrieve all abilities",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/abilities")
    public List<Ability> findAll() {
        return abilityService.findAll();
    }


    @Operation(
            description = "Get endpoint to retrieve an ability by its ID. This is useful for fetching the details of a specific ability that a user might possess.",
            summary = "Retrieve an ability by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ability not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/abilities/{abilityId}")
    public Ability getAbilityById(@PathVariable int abilityId) {
        Ability ability = abilityService.findById(abilityId);
        if (ability == null) {
            throw new RuntimeException("Ability not found for id - " + abilityId);
        }
        return ability;
    }

    @Operation(
            description = "Post endpoint to add a new ability. This allows for the creation of new abilities that can be assigned to users.",
            summary = "Add a new ability",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/abilities")
    public Ability addAbility(@RequestBody PostAbilityDto postAbilityDto) {
        return abilityService.save(postAbilityDto);
    }

    @Operation(
            description = "Put endpoint to update an existing ability. This can be used to modify the details of an ability, such as its name or associated permissions.",
            summary = "Update an ability",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ability not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/abilities")
    public Ability updateAbility(@RequestBody PutAbilityDto putAbilityDto) {
        return abilityService.save(putAbilityDto);
    }

    @Operation(
            description = "Delete endpoint to remove an ability by its ID. This is useful for deleting a specific ability that is no longer needed.",
            summary = "Delete an ability by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ability not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/abilities/{abilityId}")
    public String deleteAbilityById(@PathVariable int abilityId) {
        Ability ability = abilityService.findById(abilityId);
        if (ability == null) {
            throw new RuntimeException("Ability not found for id - " + abilityId);
        }
        abilityService.deleteById(abilityId);
        return "Deleted ability with id - " + abilityId;
    }


    @Operation(
            description = "Delete endpoint to remove all abilities. This is useful for bulk deletion of all abilities in the system.",
            summary = "Delete all abilities",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/abilities")
    public String deleteAllAbilities() {
        return abilityService.deleteAll();
    }
}
