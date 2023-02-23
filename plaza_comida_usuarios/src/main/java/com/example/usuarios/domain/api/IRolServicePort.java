package com.example.usuarios.domain.api;

import com.example.usuarios.domain.model.RolModel;

public interface IRolServicePort {
    RolModel getRol(Long rolId);
}
