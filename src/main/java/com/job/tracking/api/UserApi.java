package com.job.tracking.api;


import com.job.tracking.controller.dto.BillDto;
import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.controller.validation.groups.OnCreate;
import com.job.tracking.controller.validation.groups.OnUpdate;
import com.job.tracking.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "user management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto);

    @ApiOperation("Get all user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    List<UserDto> getAllUsers(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    })
    @ApiOperation("Get user by email")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{email}")
    UserModel getUser(@PathVariable String email);

    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user")
    UserModel updateUser(@RequestBody @Validated(OnUpdate.class) UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    })
    @ApiOperation("Delete user")
    @DeleteMapping(value = "/user/{email}")
    ResponseEntity<Void> deleteUser(@PathVariable String email);

    @ApiOperation("Get all bills")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bills")
    List<BillDto> getAllBills();

    @ApiOperation("Get all bills by amount")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bills/{amount}")
    List<BillDto> getAllBillsByAmount(@PathVariable String amount);

}
