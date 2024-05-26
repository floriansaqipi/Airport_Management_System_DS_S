package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import com.internationalairport.airportmanagementsystem.service.interfaces.SecurityCheckpointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class SecurityCheckpointRestController {

    private SecurityCheckpointService securityCheckpointService;

    @Autowired
    public SecurityCheckpointRestController(SecurityCheckpointService theSecurityCheckpointService){
        securityCheckpointService=theSecurityCheckpointService;
    }

    @Operation(
            description = "Get all security checkpoints",
            summary = "Get all security checkpoints",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all security checkpoints",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/security_checkpoints")
    public List<SecurityCheckpoint> findAllSecurityCheckpoints(){
        return securityCheckpointService.findAll();
    }

    @Operation(
            description = "Get a security checkpoint by ID",
            summary = "Get a security checkpoint by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Security checkpoint ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/security_checkpoints/{securityCheckpointId}")
    public SecurityCheckpoint getSecurityCheckpointById(@PathVariable int securityCheckpointId){
        SecurityCheckpoint securityCheckpoint=securityCheckpointService.findById(securityCheckpointId);
        if(securityCheckpoint==null){
            throw new RuntimeException("Id not found - "+securityCheckpointId);
        }
        return securityCheckpoint;
    }

    @Operation(
            description = "Add a new security checkpoint",
            summary = "Add a new security checkpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/security_checkpoints")
    public SecurityCheckpoint addSecurityCheckpoint(@RequestBody PostSecurityCheckpointDto postSecurityCheckpointDto){
        return securityCheckpointService.save(postSecurityCheckpointDto);
    }

    @Operation(
            description = "Update a security checkpoint",
            summary = "Update a security checkpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/security_checkpoints")
    public SecurityCheckpoint updateSecurityCheckpoint(@RequestBody PutSecurityCheckpointDto putSecurityCheckpointDto){
        return securityCheckpointService.save(putSecurityCheckpointDto);
    }

    @Operation(
            description = "Delete a security checkpoint by ID",
            summary = "Delete a security checkpoint by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Security checkpoint ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/security_checkpoints/{securityCheckpointId}")
    public String deleteSecurityCheckpointById(@PathVariable int securityCheckpointId){
        SecurityCheckpoint securityCheckpoint = securityCheckpointService.findById(securityCheckpointId);
        if(securityCheckpoint==null){
            throw new RuntimeException("Id not found - "+securityCheckpointId);

        }
        securityCheckpointService.deleteById(securityCheckpointId);
        return "Deleted Security Checkpoint id - "+securityCheckpointId;
    }
    @Operation(
            description = "Delete all security checkpoints",
            summary = "Delete all security checkpoints",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all security checkpoints",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/security_checkpoints")
    public String deleteAllSecurityCheckpoints() {
        return securityCheckpointService.deleteAll();
    }
}