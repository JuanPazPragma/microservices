package com.example.usuarios.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;
    private RolResponseDto rolId;
}
