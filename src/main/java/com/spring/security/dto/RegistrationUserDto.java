package com.spring.security.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String userName; //todo add validation
    private String password;
    private String confirmPassword;
    private String email;
}
