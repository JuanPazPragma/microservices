package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.dto.response.JwtResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    AuthenticationResponseDto register(UserRequestDto userRequestDto);
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
    JwtResponseDto login(AuthenticationRequestDto authenticationRequestDto);
    UserResponseDto getById(Long userId);
    AuthenticationResponseDto ownerRegister(RegisterRequestDto registerRequestDto, String token);
    AuthenticationResponseDto employeeRegister(RegisterRequestDto registerRequestDto, String token);
}
