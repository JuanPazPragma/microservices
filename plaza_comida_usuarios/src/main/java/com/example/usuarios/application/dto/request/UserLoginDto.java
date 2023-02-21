package com.example.usuarios.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String userName;
    private String password;
}
