package com.job.tracking.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.job.tracking.controller.validation.groups.OnCreate;
import com.job.tracking.controller.validation.groups.OnUpdate;
import com.job.tracking.model.enums.UserType;
import com.job.tracking.repository.entity.PaymentProvider;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotBlank(message = "password shouldn't be empty", groups = OnCreate.class)

    private String password;
    @NotBlank(message = "Repeat password shouldn't be empty", groups = OnCreate.class)

    private String repeatPassword;
    @NotBlank(message = "First name shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String firstName;
    @NotBlank(message = "First name shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String lastName;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "{email.notempty}", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(message = "enter Id fo payment provider")
    private Long paymentProviderId;
    private PaymentProvider paymentProvider;
}
