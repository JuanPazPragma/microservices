package com.example.usuarios.infrastructure.out.jpa.adapter;

import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.domain.spi.IUserPersistencePort;
import com.example.usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.usuarios.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel saveUser(UserModel userModel) {

        UserModel user = userModel;
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(userEntityMapper.toEntity(user));
        return user;
    }

}
