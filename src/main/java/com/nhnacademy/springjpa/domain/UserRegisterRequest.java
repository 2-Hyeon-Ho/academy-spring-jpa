package com.nhnacademy.springjpa.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterRequest {
    String id;

    @NotBlank
    String password;

    int age;
}
