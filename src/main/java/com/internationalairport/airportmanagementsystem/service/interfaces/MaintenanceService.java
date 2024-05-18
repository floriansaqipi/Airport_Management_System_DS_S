package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;

import java.util.List;

public interface MaintenanceService {
    List<Maintenance> findAll();
    Maintenance findById(int theId);
    Maintenance save(PutMaintenanceDto putMaintenanceDto);
    Maintenance save(PostMaintenanceDto postMaintenanceDto);
    void deleteById(int theId);

}
