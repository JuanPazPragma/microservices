package com.example.usuarios.domain.usecase;

import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.spi.IRolPersistencePort;
import com.example.usuarios.domain.spi.IUserPersistencePort;

public class RolUseCase implements IRolServicePort {
    private final IRolPersistencePort rolPersistencePort;
    public RolUseCase(IRolPersistencePort rolPersistencePort){ this.rolPersistencePort = rolPersistencePort;}

    @Override
    public RolModel getRol(Long rolId) {
        return rolPersistencePort.getRol(rolId);
    }
}
