package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.JwtResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;

public interface IUserHandler {
    UserResponseDto register(UserRequestDto userRequestDto);
    JwtResponseDto login(AuthenticationRequestDto authenticationRequestDto);
    UserResponseDto getById(Long userId);
    UserResponseDto ownerRegister(RegisterRequestDto registerRequestDto, String token);
    UserResponseDto employeeRegister(RegisterRequestDto registerRequestDto, String token);
}
