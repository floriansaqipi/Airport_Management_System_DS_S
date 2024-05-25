package com.internationalairport.airportmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "abilities")
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ability_id")
    private Integer abilityId;

    @Column(name = "entity")
    private String entity;
    @Column(name = "verb")
    private String verb;

    @Column(name = "field")
    private String field;

    @ManyToMany(mappedBy = "abilities")
    @JsonIgnoreProperties({"abilities", "users"})
    private List<Role> roles;

    @PreRemove
    public void preRemove(){
        for(Role role : roles) {
            role.getAbilities().remove(this);
        }
    }

    public Ability() {
    }

    public Ability(String entity, String verb, String field) {
        this.entity = entity;
        this.verb = verb;
        this.field = field;
    }

    public Integer getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Integer abilityId) {
        this.abilityId = abilityId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "abilityId=" + abilityId +
                ", entity='" + entity + '\'' +
                ", verb='" + verb + '\'' +
                ", field='" + field + '\'' +
                '}';
    }

    public void addRole(Role role){
        if(this.roles == null){
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }
}
