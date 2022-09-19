package com.job.tracking.service.impl;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.repository.UserRepository;
import com.job.tracking.repository.entity.UserEntity;
import com.job.tracking.service.UserService;
import com.job.tracking.service.exception.UserAlreadyExistsException;
import com.job.tracking.service.exception.UserNotFoundException;
import com.job.tracking.service.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Iterable<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            users.add(userMapper.mapUserToUserDto(userEntity));
        }
        log.info("Finding all users");
        return users;
    }

    @Override
    public List<UserDto> getAllUsers(Integer from, Integer to) {
        List<UserDto> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll(PageRequest.
                of(from, to, (Sort.by(Sort.Direction.ASC, "lastName")))).toList();
        for (UserEntity userEntity : userEntities) {
            users.add(userMapper.mapUserToUserDto(userEntity));
        }
        log.info("Finding all users from {} to {}", from, to);
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
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("creating user");
        Optional<UserEntity> persistedUser = userRepository.findByEmail(userDto.getEmail());
        if (persistedUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserEntity user = userMapper.mapUsrDtoToUserEntity(userDto);
        user = userRepository.save(user);
        log.info("User with {} email successfully created", user.getEmail());
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        log.info("update user");
        UserEntity persistedUser = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
        persistedUser = userMapper.populateUserWithPresentUserDtoFields(persistedUser, userDto);
        UserEntity storedEntity = userRepository.save(persistedUser);
        log.info("User with {} successfully updated", storedEntity.getEmail());
        return userMapper.mapUserToUserDto(persistedUser);
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        log.info("delete user");
        UserEntity user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("User with {} successfully deleted", email);
    }
}
