package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostCargoDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutCargoDto;
import com.internationalairport.airportmanagementsystem.entities.Cargo;
import com.internationalairport.airportmanagementsystem.service.interfaces.CargoService;
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

    @GetMapping("/cargo")
    public List<Cargo> findAll(){
        return cargoService.findAll();
    }

    @GetMapping("/cargo/{cargoId}")
    public Cargo getCargo(@PathVariable int cargoId){
        Cargo theCargo = cargoService.findById(cargoId);
        if(theCargo == null){
            throw new RuntimeException("Cargo id not found - " + cargoId);
        }
        return theCargo;
    }

    @PostMapping("/cargo")
    public Cargo addCargo(@RequestBody PostCargoDto postCargoDto){

        return cargoService.save(postCargoDto);
    }


    @PutMapping("/cargo")
    public Cargo updateCargo(@RequestBody PutCargoDto putCargoDto){
        Cargo dbCargo = cargoService.save(putCargoDto);
        return dbCargo;
    }

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
