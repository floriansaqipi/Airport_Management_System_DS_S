package com.internationalairport.airportmanagementsystem.entities;
import jakarta.persistence.*;

@Entity
@Table(name="airport_services")
public class AirportService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "operating_hours")
    private String operatingHours;

    public AirportService() {
    }

    public AirportService(String name, String location, String operatingHours) {
        this.name = name;
        this.location = location;
        this.operatingHours = operatingHours;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    @Override
    public String toString() {
        return "AirportService{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
