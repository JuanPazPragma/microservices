package com.example.usuarios.domain.usecase;

import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.spi.IRolPersistencePort;
import com.example.usuarios.domain.spi.IUserPersistencePort;

public class RolUseCase implements IRolServicePort {
    private final IRolPersistencePort rolPersistencePort;
    public RolUseCase(IRolPersistencePort rolPersistencePort){ this.rolPersistencePort = rolPersistencePort;}

}
