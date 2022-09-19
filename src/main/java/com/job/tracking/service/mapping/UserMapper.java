package com.job.tracking.service.mapping;

import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.repository.UserRepository;
import com.job.tracking.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {
    @Autowired
    UserRepository userRepository;

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "paymentProvider.paymentSystem", source = "paymentProvider")
    @Mapping(target = "paymentProvider.id", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract UserEntity mapUsrDtoToUserEntity(UserDto userDto);

    @Mapping(target = "paymentProvider", source = "paymentProvider.paymentSystem")
    public abstract UserDto mapUserToUserDto(UserEntity userEntity);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "paymentProvider.paymentSystem", source = "paymentProvider")
    public abstract UserEntity populateUserWithPresentUserDtoFields(@MappingTarget UserEntity user, UserDto userDto);


}
