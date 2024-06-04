package com.internationalairport.airportmanagementsystem.rest;

import com.internationalairport.airportmanagementsystem.dtos.post.PostRoleDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.exceptions.RoleAlreadyExistsException;
import com.internationalairport.airportmanagementsystem.service.interfaces.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(
            description = "Endpoint to retrieve all roles",
            summary = "Get all roles",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all roles",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/roles")
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }

    @Operation(
            description = "Endpoint to retrieve a role by ID",
            summary = "Get a role by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the role",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Role ID not found",
                            responseCode = "404"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
                    )
            }
    )
    @GetMapping("/roles/{RoleId}")
    public Role getRoleById(@PathVariable Integer RoleId) {
        Role role = roleService.findById(RoleId);
        if (role == null) {
            throw new RuntimeException("Role not found for id - " + RoleId);
        }
        return role;
    }

    @Operation(
            description = "Endpoint to add a new role",
            summary = "Add a new role",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the role",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
                    )
            }
    )
    @PostMapping("/roles")
    public Role addRole(@RequestBody PostRoleDto postRoleDto) {
        if (roleService.existsByRoleName(postRoleDto.roleName())) {
            throw new RoleAlreadyExistsException("A role with that name already exists!");
        }

        return roleService.save(postRoleDto);
    }

    @Operation(
            description = "Endpoint to update a role",
            summary = "Update a role",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the role",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
                    )
            }
    )
    @PutMapping("/roles")
    public Role updateRole(@RequestBody PutRoleDto putRoleDto) {
        Role role = roleService.findById(putRoleDto.roleId());

        if (roleService.existsByRoleName(putRoleDto.roleName()) &&
                !putRoleDto.roleName().equals(role.getRoleName())) {
            throw new RoleAlreadyExistsException("A role with that name already exists!");
        }
        return roleService.save(putRoleDto);
    }

    @Operation(
            description = "Endpoint to delete a role by ID",
            summary = "Delete a role by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the role",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
                     ),
                    @ApiResponse(
                            description = "Role ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/roles/{RoleId}")
    public ResponseEntity<String> deleteRoleById(@PathVariable Integer RoleId) {
        Role role = roleService.findById(RoleId);
        if (role == null) {
            throw new RuntimeException("Role not found for id - " + RoleId);
        }
        roleService.deleteById(RoleId);
        return new ResponseEntity<>("Role by Id - " + RoleId + " deleted!", HttpStatus.OK);
    }


    @Operation(
            description = "Endpoint to delete all roles",
            summary = "Delete all roles",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all roles",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
            ),
            }
    )
    @DeleteMapping("/roles")
    public String deleteAllRoles() {
        return roleService.deleteAll();
    }
}
