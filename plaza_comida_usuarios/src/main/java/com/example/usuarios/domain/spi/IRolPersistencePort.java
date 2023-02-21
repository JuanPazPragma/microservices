package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;

import java.util.Optional;

public interface IRolPersistencePort {
    RolModel saveRol(RolModel rolModel);

    RolModel getRol(Long rolId);

    Optional<RolEntity> getByRolName(String rolName);

}
