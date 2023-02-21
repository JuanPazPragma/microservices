package com.example.usuarios.infrastructure.out.jpa.repository;

import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
    Optional<RolEntity> findByRolEntityName(String rolName);
}
