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

    UserResponseDto getByEmail(String email);

    UserResponseDto ownerRegister(RegisterRequestDto registerRequestDto);

    UserResponseDto employeeRegister(RegisterRequestDto registerRequestDto, Long restaurantId);

    UserResponseDto clientRegister(RegisterRequestDto registerRequestDto);
}
