package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.dao.SecurityCheckpointRepository;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import com.internationalairport.airportmanagementsystem.service.interfaces.SecurityCheckpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityCheckpointServiceImpl implements SecurityCheckpointService {
    private SecurityCheckpointRepository securityCheckpointRepository;
    @Autowired
    public SecurityCheckpointServiceImpl(SecurityCheckpointRepository theSecurityChekpointRepository)
    {
        securityCheckpointRepository=theSecurityChekpointRepository;
    }
    @Override
    public SecurityCheckpoint save(SecurityCheckpoint theSecurityCheckpoint) {
        return securityCheckpointRepository.save(theSecurityCheckpoint);
    }

    @Override
    public SecurityCheckpoint findById(Integer theId) {
        Optional<SecurityCheckpoint> result = securityCheckpointRepository.findById(theId);
        SecurityCheckpoint securityCheckpoint=null;
        if(result.isPresent()){
            securityCheckpoint = result.get();
        }else{
            throw new RuntimeException("Did not found security id - "+theId);
        }
        return securityCheckpoint;
    }

    @Override
    public List<SecurityCheckpoint> findAll() {
        return securityCheckpointRepository.findAll();
    }

    @Override
    public void deleteById(Integer theId) {
        securityCheckpointRepository.deleteById(theId);
    }

    @Override
    public String deleteAll() {
        int numberOfRows= (int)securityCheckpointRepository.count();
        securityCheckpointRepository.deleteAll();
        return numberOfRows + " rows have been deleted";
    }
}
