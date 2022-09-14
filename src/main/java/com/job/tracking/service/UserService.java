package com.job.tracking.service;

import com.job.tracking.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUser(String email);

    void createUser(UserDto userDto);

    void deleteUser(String email);

    UserDto updateUser(String email, UserDto userDto);
}
