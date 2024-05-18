package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import com.internationalairport.airportmanagementsystem.service.interfaces.SecurityCheckpointService;
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

    @GetMapping("/security_checkpoints")
    public List<SecurityCheckpoint> findAllSecurityCheckpoints(){
        return securityCheckpointService.findAll();
    }

    @GetMapping("/security_checkpoints/{securityCheckpointId}")
    public SecurityCheckpoint getSecurityCheckpointById(@PathVariable int securityCheckpointId){
        SecurityCheckpoint securityCheckpoint=securityCheckpointService.findById(securityCheckpointId);
        if(securityCheckpoint==null){
            throw new RuntimeException("Id not found - "+securityCheckpointId);
        }
        return securityCheckpoint;
    }

    @PostMapping("/security_checkpoints")
    public SecurityCheckpoint addSecurityCheckpoint(@RequestBody PostSecurityCheckpointDto postSecurityCheckpointDto){
        return securityCheckpointService.save(postSecurityCheckpointDto);
    }

    //add mapping for put
    @PutMapping("/security_checkpoints")
    public SecurityCheckpoint updateSecurityCheckpoint(@RequestBody PutSecurityCheckpointDto putSecurityCheckpointDto){
        return securityCheckpointService.save(putSecurityCheckpointDto);
    }

    @DeleteMapping("/security_checkpoints/{securityCheckpointId}")
    public String deleteSecurityCheckpointById(@PathVariable int securityCheckpointId){
        SecurityCheckpoint securityCheckpoint = securityCheckpointService.findById(securityCheckpointId);
        if(securityCheckpoint==null){
            throw new RuntimeException("Id not found - "+securityCheckpointId);

        }
        securityCheckpointService.deleteById(securityCheckpointId);
        return "Deleted Security Checkpoint id - "+securityCheckpointId;
    }
    @DeleteMapping("/security_checkpoints")
    public String deleteAllSecurityCheckpoints() {
        return securityCheckpointService.deleteAll();
    }
}