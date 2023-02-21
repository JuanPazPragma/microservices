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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel saveUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.toEntity(userModel);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        return userModel;
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsUserByName(String name) {
        return userRepository.existsByEmail(name);
    }

    @Override
    public Optional<UserEntity> getByUser(String name) {
        return userRepository.findByName(name);
    }

}
