package com.job.tracking.service;

import com.job.tracking.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    List<UserDto> getAllUsers(Integer from, Integer to);

    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    void deleteUser(String email);

    UserDto updateUser(UserDto userDto);
}
