package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.RoleRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.Role;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.UserMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private UserEntityRepository userEntityRepository;

    private RoleRepository roleRepository;
    private UserMapper userMapper;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository theUserEntityRepository,
                                 UserMapper theUserMapper,
                                 RoleRepository roleRepository) {
        userEntityRepository = theUserEntityRepository;
        userMapper = theUserMapper;
        this.roleRepository = roleRepository;
    }
    @Override
    public UserEntity save(PostUserDto postUserDto) {
        UserEntity userEntity = userMapper.postToUser(postUserDto);
        Role employeeRole = roleRepository.findByRoleName("ADMIN").get();
        userEntity.setRole(employeeRole);
        return userEntityRepository.save(userEntity);
    }

    @Override
    public UserEntity save(PutUserDto putUserEntityDto) {
        UserEntity userEntity = userMapper.putToUser(putUserEntityDto);
        userEntity.setRole(this.findById(putUserEntityDto.userId()).getRole());
        return userEntityRepository.save(userEntity);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userEntityRepository.existsByUsername(username);
    }

    @Override
    public UserEntity findById(Integer userId) {
        Optional<UserEntity> result = userEntityRepository.findById(userId);
        UserEntity theUserEntity = null;
        if (result.isPresent()) {
            theUserEntity = result.get();
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
        return theUserEntity;
    }

    @Override
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> result = userEntityRepository.findByUsername(username);
        UserEntity theUserEntity = null;
        if (result.isPresent()) {
            theUserEntity = result.get();
        } else {
            throw new RuntimeException("User with username " + username + " not found");
        }
        return theUserEntity;
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityRepository.findAll();
    }

    @Override
    public void deleteById(Integer userId) {
        userEntityRepository.deleteById(userId);
    }

    @Override
    public String deleteAll() {
        int numberOfUserEntitys = (int) userEntityRepository.count();
        userEntityRepository.deleteAll();
        return numberOfUserEntitys + " Users have been deleted";
    }
}
