package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.AbilityRepository;
import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAbilityDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.mappers.AbilityMapper;
import com.internationalairport.airportmanagementsystem.mappers.RoleMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbilityServiceImpl implements AbilityService {
    private AbilityRepository abilityRepository;
    private AbilityMapper abilityMapper;

    @Autowired
    public AbilityServiceImpl(AbilityRepository abilityRepository, AbilityMapper abilityMapper) {
        this.abilityRepository = abilityRepository;
        this.abilityMapper = abilityMapper;
    }
    @Override
    public Ability save(PostAbilityDto postAbilityDto) {
        Ability ability = abilityMapper.postToAbility(postAbilityDto);
        return abilityRepository.save(ability);
    }

    @Override
    public Ability save(PutAbilityDto putAbilityDto) {
        Ability ability = abilityMapper.putToAbility(putAbilityDto);
        return abilityRepository.save(ability);
    }


    @Override
    public Ability findById(Integer abilityId) {
        Optional<Ability> result = abilityRepository.findById(abilityId);
        Ability ability = null;
        if (result.isPresent()) {
            ability = result.get();
        } else {
                throw new RuntimeException("Ability with ID " + abilityId + " not found");
        }
        return ability;
    }

    @Override
    public List<Ability> findAll() {
        return abilityRepository.findAll();
    }

    @Override
    public void deleteById(Integer abilityId) {
        abilityRepository.deleteById(abilityId);
    }

    @Override
    public String deleteAll() {
        int numberOfAbilities = (int) abilityRepository.count();
        abilityRepository.deleteAll();
        return numberOfAbilities + " Roles have been deleted";
    }
}
