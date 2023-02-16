package com.example.usuarios.domain.api;

import com.example.usuarios.domain.model.UserModel;

public interface IUserServicePort {
    UserModel saveUser(UserModel userModel);
}
