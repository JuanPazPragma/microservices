package com.example.usuarios.domain.usecase;

import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.spi.IRolPersistencePort;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    @Override
    public RolModel saveRol(RolModel rolModel) {
        return rolPersistencePort.saveRol(rolModel);
    }

    @Override
    public RolModel getRol(Long rolId) {
        return rolPersistencePort.getRol(rolId);
    }

    @Override
    public Optional<RolEntity> getByRolName(String rolName) {
        return rolPersistencePort.getByRolName(rolName);
    }
}
