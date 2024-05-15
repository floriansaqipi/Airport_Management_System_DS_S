package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAircraftDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.service.interfaces.AbilityService;
import com.internationalairport.airportmanagementsystem.service.interfaces.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AbilityRestController {

    private AbilityService abilityService;

    @Autowired
    public AbilityRestController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @GetMapping("/abilities")
    public List<Ability> findAll() {
        return abilityService.findAll();
    }

    @GetMapping("/abilities/{abilityId}")
    public Ability getAbilityById(@PathVariable int abilityId) {
        Ability ability = abilityService.findById(abilityId);
        if (ability == null) {
            throw new RuntimeException("Ability not found for id - " + abilityId);
        }
        return ability;
    }

    @PostMapping("/abilities")
    public Ability addAbility(@RequestBody PostAbilityDto postAbilityDto) {
        return abilityService.save(postAbilityDto);
    }

    @PutMapping("/abilities")
    public Ability updateAbility(@RequestBody PutAbilityDto putAbilityDto) {
        return abilityService.save(putAbilityDto);
    }

    @DeleteMapping("/abilities/{abilityId}")
    public String deleteAbilityById(@PathVariable int abilityId) {
        Ability ability = abilityService.findById(abilityId);
        if (ability == null) {
            throw new RuntimeException("Ability not found for id - " + abilityId);
        }
        abilityService.deleteById(abilityId);
        return "Deleted ability with id - " + abilityId;
    }

    @DeleteMapping("/abilities")
    public String deleteAllAbilities() {
        return abilityService.deleteAll();
    }
}
