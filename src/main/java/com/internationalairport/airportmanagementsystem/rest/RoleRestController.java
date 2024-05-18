package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class RoleRestController {
    private RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }

    @GetMapping("/roles/{RoleId}")
    public Role getRoleById(@PathVariable Integer RoleId) {
        Role role = roleService.findById(RoleId);
        if (role == null) {
            throw new RuntimeException("Role not found for id - " + RoleId);
        }
        return role;
    }

    @PostMapping("/roles")
    public Role addRole(@RequestBody PostRoleDto postRoleDto) {
        return roleService.save(postRoleDto);
    }

    @PutMapping("/roles")
    public Role updateRole(@RequestBody PutRoleDto putRoleDto) {
        return roleService.save(putRoleDto);
    }

    @DeleteMapping("/roles/{RoleId}")
    public String deleteRoleById(@PathVariable Integer RoleId) {
        Role role = roleService.findById(RoleId);
        if (role == null) {
            throw new RuntimeException("Role not found for id - " + RoleId);
        }
        roleService.deleteById(RoleId);
        return "Deleted Role with id - " + RoleId;
    }

    @DeleteMapping("/roles")
    public String deleteAllRoles() {
        return roleService.deleteAll();
    }
}
