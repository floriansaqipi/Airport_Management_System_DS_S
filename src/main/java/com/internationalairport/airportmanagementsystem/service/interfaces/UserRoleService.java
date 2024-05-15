package com.internationalairport.airportmanagementsystem.service.interfaces;

import com.internationalairport.airportmanagementsystem.dtos.post.PostFlightCrewDto;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserRoleDto;
import com.internationalairport.airportmanagementsystem.entities.Flight;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;

public interface UserRoleService {
    UserEntity save(PostUserRoleDto postUserRoleDto);
    void deleteByUserIdAndRoleId(Integer userId, Integer roleId);
}
