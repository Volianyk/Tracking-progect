package com.job.tracking.service.mapping;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.repository.UserRepository;
import com.job.tracking.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {
    @Autowired
    UserRepository userRepository;

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract UserEntity mapUsrDtoToUserEntity(UserDto userDto);

    public abstract UserDto mapUserToUserDto(UserEntity userEntity);
}
