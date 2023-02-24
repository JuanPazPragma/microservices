package com.example.usuarios.infrastructure.out.jpa.adapter;

import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.domain.spi.IUserPersistencePort;
import com.example.usuarios.infrastructure.exception.NoDataFoundException;
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

        UserEntity user = userRepository.save(userEntity);

        return userEntityMapper.toUserModel(user);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel findUserByEmailModel(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        UserEntity userEntity = userEntityOptional.get();
        UserModel userModel = userEntityMapper.toUserModel(userEntity);
        return userModel;
    }

    @Override
    public UserModel getById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(NoDataFoundException::new);
        return userEntityMapper.toUserModel(userEntity);
    }

}
