package com.job.tracking.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Person {
    @NotEmpty(message = "Enter name")
    @Size(min = 2, max = 30, message = "Enter correct name")
    private String name;
    @Email
    private String email;
    private String password;
}
