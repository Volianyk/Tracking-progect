package com.job.tracking.service.impl;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.model.Task;
import com.job.tracking.repository.UserRepository;
import com.job.tracking.repository.entity.TaskEntity;
import com.job.tracking.repository.entity.UserEntity;
import com.job.tracking.service.UserService;
import com.job.tracking.service.exception.UserNotFoundException;
import com.job.tracking.service.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            users.add(userMapper.mapUserToUserDto(userEntity));
        }
        return users;
    }

    @Override
    public UserDto getUser(String email) {
        log.info("Finding user by {} email...", email);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        log.info("User with {} email is found", email);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    // @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("creating user");
        UserEntity user = userMapper.mapUsrDtoToUserEntity(userDto);
        user = userRepository.save(user);
        log.info("User with {} email successfully created", user.getEmail());
        return userMapper.mapUserToUserDto(user);
    }

    @Override
//@Transactional
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("update user");
        UserEntity persistedUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new); //improve
        persistedUser = userMapper.populateUserWithPresentUserDtoFields(persistedUser, userDto);
        UserEntity storedEntity = userRepository.save(persistedUser);
        log.info("User with {} successfully updated", storedEntity.getEmail());
        return userMapper.mapUserToUserDto(persistedUser);
    }

    @Override
    public void deleteUser(String email) {
        log.info("delete user");
        UserEntity user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("User with {} successfully deleted", email);
    }
}
