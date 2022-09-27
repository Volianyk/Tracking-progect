package com.job.tracking.service.impl;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.repository.PaymentProviderRepository;
import com.job.tracking.repository.UserRepository;
import com.job.tracking.repository.entity.PaymentProvider;
import com.job.tracking.repository.entity.UserEntity;
import com.job.tracking.service.UserService;
import com.job.tracking.service.exception.IncorrectRepeatPasswordException;
import com.job.tracking.service.exception.PaymentSystemNotFoundException;
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
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PaymentProviderRepository paymentProviderRepository;


    @Override
    public List<UserDto> getAllUsersByParameter(Integer from, Integer to) {
        List<UserDto> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll(PageRequest.
                of(from, to, (Sort.by(Sort.Direction.ASC, "lastName")))).toList();
        for (UserEntity userEntity : userEntities) {
            users.add(userMapper.mapUserEntityToUserDto(userEntity));
        }
        log.info("Finding all users from {} to {}", from, to);
        return users;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll((Sort.by(Sort.Direction.ASC, "lastName")));
        for (UserEntity userEntity : userEntities) {
            users.add(userMapper.mapUserEntityToUserDto(userEntity));
        }
        log.info("Finding all users");
        return users;
    }

    @Override
    public UserDto getUser(String email) {
        log.info("Finding user by {} email...", email);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        log.info("User with {} email is found", email);
        return userMapper.mapUserEntityToUserDto(user);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("creating user");
        Optional<UserEntity> persistedUser = userRepository.findByEmail(userDto.getEmail());
        if (persistedUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (!Objects.equals(userDto.getPassword(), userDto.getRepeatPassword())) {
            throw new IncorrectRepeatPasswordException();
        }
        UserEntity userEntity = userMapper.mapUserDtoToUserEntity(userDto);
        Long paymentProviderId = userDto.getPaymentProviderId();
        PaymentProvider paymentProvider = paymentProviderRepository
                .findById(paymentProviderId).orElseThrow(() -> new PaymentSystemNotFoundException());
        userEntity.setPaymentProvider(paymentProvider);
        userRepository.save(userEntity);
        log.info("User with {} email successfully created", userDto.getEmail());
        return userMapper.mapUserEntityToUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        log.info("update user");
        UserEntity persistedUser = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        persistedUser = userMapper.populateUserWithPresentUserDtoFields(persistedUser, userDto);

        Long paymentProviderId = userDto.getPaymentProviderId();
        PaymentProvider paymentProvider = paymentProviderRepository
                .findById(paymentProviderId).orElseThrow(() -> new PaymentSystemNotFoundException());
        persistedUser.setPaymentProvider(paymentProvider);

        UserEntity storedEntity = userRepository.save(persistedUser);
        log.info("User with {} successfully updated", storedEntity.getEmail());
        return userMapper.mapUserEntityToUserDto(persistedUser);
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
