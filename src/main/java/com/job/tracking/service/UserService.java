package com.job.tracking.service;

import com.job.tracking.controller.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsersByParameter(Integer from, Integer to);

    List<UserDto> getAllUsers();

    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    void deleteUser(String email);

    UserDto updateUser(UserDto userDto);

}
