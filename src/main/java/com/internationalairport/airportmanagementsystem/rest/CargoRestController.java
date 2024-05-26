package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCargoDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.service.interfaces.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class CargoRestController {

    private CargoService cargoService;

    @Autowired
    public CargoRestController(CargoService theCargoService){
        cargoService = theCargoService;
    }

    @Operation(
            description = "Get endpoint to retrieve all cargo records",
            summary = "Retrieve all cargo records",
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
    @GetMapping("/cargo")
    public List<Cargo> findAll(){
        return cargoService.findAll();
    }

    @Operation(
            description = "Get endpoint to retrieve a cargo record by its ID",
            summary = "Retrieve a cargo record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cargo not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/cargo/{cargoId}")
    public Cargo getCargo(@PathVariable int cargoId){
        Cargo theCargo = cargoService.findById(cargoId);
        if(theCargo == null){
            throw new RuntimeException("Cargo id not found - " + cargoId);
        }
        return theCargo;
    }

    @Operation(
            description = "Post endpoint to add a new cargo record",
            summary = "Add a new cargo record",
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
    @PostMapping("/cargo")
    public Cargo addCargo(@RequestBody PostCargoDto postCargoDto){

        return cargoService.save(postCargoDto);
    }


    @Operation(
            description = "Put endpoint to update an existing cargo record",
            summary = "Update a cargo record",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cargo not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/cargo")
    public Cargo updateCargo(@RequestBody PutCargoDto putCargoDto){
        Cargo dbCargo = cargoService.save(putCargoDto);
        return dbCargo;
    }

    @Operation(
            description = "Delete endpoint to remove a cargo record by its ID",
            summary = "Delete a cargo record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cargo not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/cargo/{cargoId}")
    public String deleteCargo(@PathVariable int cargoId){
        Cargo tempCargo = cargoService.findById(cargoId);
        if(tempCargo == null){
            throw new RuntimeException("Cargo id not found - " + cargoId);
        }
        cargoService.deleteById(cargoId);
        return "Deleted Cargo id - " + cargoId;
    }
}
