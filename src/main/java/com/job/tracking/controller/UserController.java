package com.job.tracking.controller;

import com.job.tracking.controller.assembler.UserAssembler;
import com.job.tracking.controller.dto.BillDto;
import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.model.UserModel;
import com.job.tracking.service.BillService;
import com.job.tracking.service.UserService;
import com.job.tracking.service.mapping.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private BillService billService;

    @ApiOperation("Get all user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<UserDto> getAllUsers(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {
        if (from != null && to != null) {
            return userService.getAllUsers(from, to);
        }
        return userService.getAllUsers();
    }

    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{email}")
    public UserModel getUser(@PathVariable String email) {
        UserDto outUserDto = userService.getUser(email);
        return userAssembler.toModel(outUserDto);
    }

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserModel createUser(@RequestBody @Validated UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user")
    public UserModel updateUser(@RequestBody @Validated UserDto userDto) {
        UserDto outUserDto = userService.updateUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @ApiOperation("Delete user")
    @DeleteMapping(value = "/user/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bills")
    public List<BillDto> getAllBills() {
        return billService.getAllRecipient();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bills/{amount}")
    public List<BillDto> getAllBillsByAmount(@PathVariable String amount) {
        return billService.getBillsByAmount(amount);
    }


}
