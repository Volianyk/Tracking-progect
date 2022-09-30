package com.job.tracking.controller;

import com.job.tracking.api.UserApi;
import com.job.tracking.controller.assembler.UserAssembler;
import com.job.tracking.controller.dto.BillDto;
import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.model.UserModel;
import com.job.tracking.service.BillService;
import com.job.tracking.service.UserService;
import com.job.tracking.service.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private BillService billService;

    @Override
    public List<UserDto> getAllUsers(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {

        if (from != null && to != null) {
            return userService.getAllUsersByParameter(from, to);
        }
        return userService.getAllUsers();
    }

    @Override
    public UserModel getUser(String email) {
        UserDto outUserDto = userService.getUser(email);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel createUser(UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel updateUser(UserDto userDto) {
        UserDto outUserDto = userService.updateUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<BillDto> getAllBills() {
        return billService.getAllRecipient();
    }

    @Override
    public List<BillDto> getAllBillsByAmount(String amount) {
        return billService.getBillsByAmount(amount);
    }

}
