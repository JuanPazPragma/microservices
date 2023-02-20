package com.example.usuarios.application.handler.impl;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.handler.IJwtHandler;
import com.example.usuarios.application.handler.IUserHandler;
import com.example.usuarios.application.mapper.request.IUserRequestMapper;
import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IJwtHandler jwtHandler;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        RolModel rolModel = new RolModel();
        rolModel.setId(userRequestDto.getRolId());

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        userServicePort.saveUser(userModel);
    }

    @Override
    public AuthenticationResponseDto register(UserRequestDto userRequestDto) {
        var user = UserEntity.builder()
                .name(userRequestDto.getName())
                .lastName(userRequestDto.getLastName())
                .idNumber(userRequestDto.getIdNumber())
                .phone(userRequestDto.getPhone())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        var jwtToken = jwtHandler.generateToken(user);

        RolModel rolModel = new RolModel();
        rolModel.setId(userRequestDto.getRolId());

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        userServicePort.saveUser(userModel);

        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );

        var user = userServicePort.findUserByEmail(authenticationRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtHandler.generateToken(user);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }
}
