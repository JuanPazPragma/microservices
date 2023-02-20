package com.example.usuarios.infrastructure.out.jpa.adapter;

import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.handler.IJwtHandler;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.domain.spi.IUserPersistencePort;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import com.example.usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.usuarios.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IJwtHandler jwtHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponseDto saveUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.toEntity(userModel);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        var jwtToken = jwtHandler.generateToken(userEntity);

        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
