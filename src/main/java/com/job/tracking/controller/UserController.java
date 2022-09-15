package com.job.tracking.controller;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.repository.entity.UserEntity;
import com.job.tracking.service.UserService;
import com.job.tracking.service.mapping.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Api(tags = "User management API")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody @Validated UserDto userDto) {
        userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{email}")
    public UserDto updateUser(@PathVariable String email, @RequestBody @Validated UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    @DeleteMapping(value = "/user/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }
}
