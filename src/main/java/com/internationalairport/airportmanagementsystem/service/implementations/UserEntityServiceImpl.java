package com.internationalairport.airportmanagementsystem.service.implementations;

import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.dtos.post.PostUserDto;
import com.internationalairport.airportmanagementsystem.dtos.put.PutUserDto;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import com.internationalairport.airportmanagementsystem.mappers.UserMapper;
import com.internationalairport.airportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private UserEntityRepository userEntityRepository;
    private UserMapper userMapper;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository theUserEntityRepository, UserMapper theUserMapper) {
        userEntityRepository = theUserEntityRepository;
        userMapper = theUserMapper;
    }
    @Override
    public UserEntity save(PostUserDto postUserDto) {
        UserEntity userEntity = userMapper.postToUser(postUserDto);
        return userEntityRepository.save(userEntity);
    }

    @Override
    public UserEntity save(PutUserDto putUserEntityDto) {
        UserEntity userEntity = userMapper.putToUser(putUserEntityDto);
        return userEntityRepository.save(userEntity);
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
