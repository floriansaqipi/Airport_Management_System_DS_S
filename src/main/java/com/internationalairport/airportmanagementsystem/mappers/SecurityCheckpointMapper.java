package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutSecurityCheckpointDto;
import com.internationalairport.airportmanagementsystem.entities.SecurityCheckpoint;
import org.springframework.stereotype.Service;

@Service
public class SecurityCheckpointMapper {
    public SecurityCheckpoint postToSecurityCheckpoint(PostSecurityCheckpointDto postSecurityCheckpointDto) {
        SecurityCheckpoint SecurityCheckpoint = new SecurityCheckpoint(
                postSecurityCheckpointDto.location(),
                postSecurityCheckpointDto.operatingHours()
        );
        SecurityCheckpoint.setCheckpointId(0);

        return SecurityCheckpoint;
    }

    public SecurityCheckpoint putToSecurityCheckpoint(PutSecurityCheckpointDto putSecurityCheckpointDto) {
        SecurityCheckpoint SecurityCheckpoint = new SecurityCheckpoint(
                putSecurityCheckpointDto.location(),
                putSecurityCheckpointDto.operatingHours()
        );
        SecurityCheckpoint.setCheckpointId(putSecurityCheckpointDto.checkpointId());

        return SecurityCheckpoint;
    }
}
