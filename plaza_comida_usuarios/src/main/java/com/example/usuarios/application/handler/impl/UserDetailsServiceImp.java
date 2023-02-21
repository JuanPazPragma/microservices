package com.example.usuarios.application.handler.impl;

import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import com.example.usuarios.infrastructure.out.jpa.entity.UserMainEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserHandler userHandler;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userHandler.getByUserName(username).get();
        return UserMainEntity.build(userEntity);
    }
}
