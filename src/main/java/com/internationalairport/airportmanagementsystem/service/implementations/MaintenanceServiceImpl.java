package com.internationalairport.airportmanagementsystem.service.implementations;


import com.internationalairport.airportmanagementsystem.dao.MaintenanceRepository;
import com.internationalairport.airportmanagementsystem.entities.Maintenance;
import com.internationalairport.airportmanagementsystem.service.interfaces.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository theMaintenanceRepository) {
        maintenanceRepository = theMaintenanceRepository;
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
    public Maintenance save(Maintenance theMaintenance) {
        return maintenanceRepository.save(theMaintenance);
    }

    @Override
    public void deleteById(int theId) {
        maintenanceRepository.deleteById(theId);
    }
}
