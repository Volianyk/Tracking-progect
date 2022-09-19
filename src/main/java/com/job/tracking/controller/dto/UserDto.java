package com.job.tracking.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserDto {
    @NotBlank(message = "firstName should not be blank")
    private String firstName;

    @NotBlank(message = "lastName should not be blank")
    private String lastName;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "{email.notempty}")
    private String email;

    private String paymentProvider;
}
