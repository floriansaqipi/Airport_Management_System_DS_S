package com.internationalairport.airportmanagementsystem.service.implementations;


import com.internationalairport.airportmanagementsystem.daos.MaintenanceRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostMaintenanceDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutMaintenanceDto;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import com.internationalairport.airportmanagementsystem.mappers.MaintenanceMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private MaintenanceRepository maintenanceRepository;
    private MaintenanceMapper maintenanceMapper;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository theMaintenanceRepository, MaintenanceMapper theMaintenanceMapper) {
        maintenanceRepository = theMaintenanceRepository;
        maintenanceMapper = theMaintenanceMapper;
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance findById(int theId) {
        Optional<Maintenance> result = maintenanceRepository.findById(theId);
        Maintenance theMaintenance = null;
        if (result.isPresent()) {
            theMaintenance = result.get();
        }
        else {
            throw new RuntimeException("Did not find maintenance id - " + theId);
        }
        return theMaintenance;
    }
    @Override
    public Maintenance save(PutMaintenanceDto putMaintenanceDto) {
        Maintenance maintenance = maintenanceMapper.putToMaintenance(putMaintenanceDto);
        return maintenanceRepository.save(maintenance);
    }
    @Override
    public Maintenance save(PostMaintenanceDto postMaintenanceDto) {
        Maintenance maintenance = maintenanceMapper.postToMaintenance(postMaintenanceDto);
        return maintenanceRepository.save(maintenance);
    }
    @Override
    public void deleteById(int theId) {
        maintenanceRepository.deleteById(theId);
    }
}
