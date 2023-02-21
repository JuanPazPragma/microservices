package com.example.usuarios.application.handler.impl;

import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.handler.IJwtHandler;
import com.example.usuarios.application.handler.IUserHandler;
import com.example.usuarios.application.mapper.request.IUserRequestMapper;
import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IJwtHandler jwtHandler;
    private final AuthenticationManager authenticationManager;


    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        RolModel rolModel = new RolModel();
        rolModel.setId(userRequestDto.getRolId());

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        userServicePort.saveUser(userModel);
    }

    @Override
    public Optional<UserEntity> getByUserName(String userName) {
        return userServicePort.getByUserName(userName);
    }

    @Override
    public Boolean existsByUserName(String name) {
        return userServicePort.existsUserByName(name);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userServicePort.existsUserByEmail(email);
    }

}
