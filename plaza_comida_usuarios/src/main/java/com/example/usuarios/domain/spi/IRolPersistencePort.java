package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.RolModel;

public interface IRolPersistencePort {
    RolModel getRol(Long rolId);
}
