package com.internationalairport.airportmanagementsystem.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "security_checkpoints")
public class SecurityCheckpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkpoint_id")
    private Integer checkpointId;

    @Column(name = "location")
    private String location;


    @Column(name = "operating_hours")
    private String operatingHours;

    public SecurityCheckpoint() {
    }

    public SecurityCheckpoint(String location, String operatingHours) {
        this.location = location;
        this.operatingHours = operatingHours;
    }

    public Integer getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Integer checkpointId) {
        this.checkpointId = checkpointId;
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
        return "SecurityCheckpoint{" +
                "checkpointId=" + checkpointId +
                ", location='" + location + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
