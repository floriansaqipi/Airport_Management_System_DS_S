package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import org.springframework.stereotype.Service;

@Service
public class AbilityMapper {
    public Ability postToAbility(PostAbilityDto postAbilityDto) {
        Ability ability = new Ability(
                postAbilityDto.entity(),
                postAbilityDto.verb(),
                postAbilityDto.field()
        );
        ability.setAbilityId(0);

        return ability;
    }

    public Ability putToAbility(PutAbilityDto putAbilityDto) {
        Ability ability = new Ability(
                putAbilityDto.entity(),
                putAbilityDto.verb(),
                putAbilityDto.field()
        );
        ability.setAbilityId(putAbilityDto.abilityId());

        return ability;
    }

}
