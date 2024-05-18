package com.internationalairport.airportmanagementsystem.mappers;

import com.internationalairport.airportmanagementsystem.dtos.post.PostAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutAircraftDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.entities.Aircraft;
import com.internationalairport.airportmanagementsystem.entities.Airline;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceMapper {
    public Maintenance postToMaintenance(PostMaintenanceDto postMaintenanceDto) {
        Maintenance maintenance = new Maintenance(
                postMaintenanceDto.date(),
                postMaintenanceDto.type(),
                postMaintenanceDto.description()
        );
        maintenance.setMaintenanceId(0);

        if(postMaintenanceDto.aircraftId() != null){
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(postMaintenanceDto.aircraftId());
            maintenance.setAircraft(aircraft);
        }
        return maintenance;
    }

    public Maintenance putToMaintenance(PutMaintenanceDto putMaintenanceDto) {
        Maintenance maintenance = new Maintenance(
                putMaintenanceDto.date(),
                putMaintenanceDto.type(),
                putMaintenanceDto.description()
        );
        maintenance.setMaintenanceId(putMaintenanceDto.maintenanceId());

        if(putMaintenanceDto.aircraftId() != null){
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(putMaintenanceDto.aircraftId());
            maintenance.setAircraft(aircraft);
        }
        return maintenance;
    }
}
