package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.request.UserRequestDto;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);
}
