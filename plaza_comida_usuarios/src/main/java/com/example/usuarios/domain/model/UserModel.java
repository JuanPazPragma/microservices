package com.example.usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {

    private Long id;
    private String name;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;
    private String password;
    private RolModel rolId;
}
