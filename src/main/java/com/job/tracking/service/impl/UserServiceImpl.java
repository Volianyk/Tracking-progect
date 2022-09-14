package com.job.tracking.service.impl;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.model.Task;
import com.job.tracking.repository.UserRepository;
import com.job.tracking.repository.entity.TaskEntity;
import com.job.tracking.repository.entity.UserEntity;
import com.job.tracking.service.UserService;
import com.job.tracking.service.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto getUser(String email) {
        return null;
    }

    @Override
    public void createUser(UserDto userDto) {

    }
    public void createTask(UserDto userDto) {
        UserEntity user = userMapper.mapUsrDtoToUserEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        return null;
    }
}
