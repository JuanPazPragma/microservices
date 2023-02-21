package com.example.usuarios.infrastructure.out.jpa.repository;

import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);
    boolean existsByName (String name);
    boolean existsByEmail (String email);

}
