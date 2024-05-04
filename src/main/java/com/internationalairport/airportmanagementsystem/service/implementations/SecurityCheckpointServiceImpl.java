package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.SecurityCheckpointRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import com.internationalairport.airportmanagementsystem.mappers.SecurityCheckpointMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.SecurityCheckpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityCheckpointServiceImpl implements SecurityCheckpointService {
    private SecurityCheckpointRepository securityCheckpointRepository;
    private SecurityCheckpointMapper securityCheckpointMapper;
    @Autowired
    public SecurityCheckpointServiceImpl(SecurityCheckpointRepository theSecurityChekpointRepository, SecurityCheckpointMapper theSecurityCheckpointMapper)
    {
        securityCheckpointRepository=theSecurityChekpointRepository;
        securityCheckpointMapper=theSecurityCheckpointMapper;
    }

    @Override
    public SecurityCheckpoint save(PostSecurityCheckpointDto postSecurityCheckpointDto) {
        SecurityCheckpoint securityCheckpoint = securityCheckpointMapper.postToSecurityCheckpoint(postSecurityCheckpointDto);
        return securityCheckpointRepository.save(securityCheckpoint);
    }

    @Override
    public SecurityCheckpoint save(PutSecurityCheckpointDto putSecurityCheckpointDto) {
        SecurityCheckpoint securityCheckpoint = securityCheckpointMapper.putToSecurityCheckpoint(putSecurityCheckpointDto);
        return securityCheckpointRepository.save(securityCheckpoint);
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
