package com.example.usuarios.application.handler.impl;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.dto.response.JwtResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.application.handler.IJwtHandler;
import com.example.usuarios.application.handler.IUserHandler;
import com.example.usuarios.application.mapper.request.IRolRequestMapper;
import com.example.usuarios.application.mapper.request.IUserRequestMapper;
import com.example.usuarios.application.mapper.response.IRolResponseMapper;
import com.example.usuarios.application.mapper.response.IUserResponseMapper;
import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.exception.NoDataFoundException;
import com.example.usuarios.infrastructure.exception.NotEnoughPrivileges;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IJwtHandler jwtHandler;
    private final AuthenticationManager authenticationManager;
    private final IRolResponseMapper rolResponseMapper;


    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        RolModel rolModel = new RolModel();
        rolModel.setId(userRequestDto.getRolId());

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        userServicePort.saveUser(userModel);
    }

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        RolModel rolModel = new RolModel();
        rolModel.setId(userRequestDto.getRolId());

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        //userResponseMapper.toResponse(userModel, rolResponseMapper.toResponse(rolModel));

        return userRequestMapper.toDto(userServicePort.saveUser(userModel));
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword());

        authenticationManager.authenticate(credentials);

        var user = userServicePort.findUserByEmail(authenticationRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtHandler.generateToken(user);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }

    @Override
    public JwtResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
        JwtResponseDto jwtResponseDto = new JwtResponseDto();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        var user = userServicePort.findUserByEmail(authenticationRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtHandler.generateToken(user);

        jwtResponseDto.setToken(jwtToken);
        jwtResponseDto.setBearer(userEntity.getEmail());
        jwtResponseDto.setUserName(userEntity.getName());
        jwtResponseDto.setAuthorities(userEntity.getAuthorities());

        return jwtResponseDto;
    }

    @Override
    public UserResponseDto getById(Long userId) {
        return userRequestMapper.toDto(userServicePort.getById(userId));
    }

    @Override
    public UserResponseDto ownerRegister(RegisterRequestDto registerRequestDto, String token) {

        String email = jwtHandler.extractUserName(token);


        Optional<UserEntity> userEntity = userServicePort.findUserByEmail(email);

        if (userEntity.isEmpty()) {
            throw new NoDataFoundException();
        }

        Long rolId = userEntity.get().getRolId().getId();

        //Id Administrador = 1
        if (rolId != 1L) {
            throw new NotEnoughPrivileges();
        }

        //Id propietario = 2
        RolModel rolModel = new RolModel();
        rolModel.setId(2L);

        UserRequestDto userRequestDto = userRequestMapper.toUserRequestDto(registerRequestDto);

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        return userRequestMapper.toDto(userServicePort.saveUser(userModel));
    }

    @Override
    public UserResponseDto employeeRegister(RegisterRequestDto registerRequestDto, String token) {
        String email = jwtHandler.extractUserName(token);


        Optional<UserEntity> userEntity = userServicePort.findUserByEmail(email);

        if (userEntity.isEmpty()) {
            throw new NoDataFoundException();
        }

        Long rolId = userEntity.get().getRolId().getId();

        //Id propietario = 2
        if (rolId != 2L) {
            throw new NotEnoughPrivileges();
        }

        //Id empleado = 3
        RolModel rolModel = new RolModel();
        rolModel.setId(3L);

        UserRequestDto userRequestDto = userRequestMapper.toUserRequestDto(registerRequestDto);

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        return userRequestMapper.toDto(userServicePort.saveUser(userModel));
    }

}
