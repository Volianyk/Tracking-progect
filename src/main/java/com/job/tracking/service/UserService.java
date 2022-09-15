package com.job.tracking.service;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.repository.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    void deleteUser(String email);

    UserDto updateUser(String email, UserDto userDto);
}
