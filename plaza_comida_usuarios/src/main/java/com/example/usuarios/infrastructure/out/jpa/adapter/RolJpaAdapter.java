package com.example.usuarios.infrastructure.out.jpa.adapter;

import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import com.example.usuarios.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.example.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolServicePort {

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;


    @Override
    public RolModel saveRol(RolModel rolModel) {
        RolEntity rolEntity = rolRepository.save(rolEntityMapper.toEntity(rolModel));
        return rolEntityMapper.toRolModel(rolEntity);
    }

    @Override
    public RolModel getRol(Long rolId) {
        return null;
    }

    @Override
    public Optional<RolEntity> getByRolName(String rolName) {
        return rolRepository.findByRolEntityName(rolName);
    }
}
