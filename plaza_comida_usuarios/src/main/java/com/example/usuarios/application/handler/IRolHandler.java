package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.request.RolRequestDto;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;

import java.util.Optional;

public interface IRolHandler {
    Optional<RolEntity> getByRolName(String rolName);
    void saveRol(RolRequestDto rol);
}
