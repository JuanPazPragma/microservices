package com.example.usuarios.infrastructure.out.jpa.adapter;

import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.spi.IRolPersistencePort;
import com.example.usuarios.infrastructure.exception.NoDataFoundException;
import com.example.usuarios.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.example.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {
    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    @Override
    public RolModel getRol(Long rolId) {
        return rolEntityMapper.toRolModel(rolRepository.findById(rolId).orElseThrow());
    }
}
