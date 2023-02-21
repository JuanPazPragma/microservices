package com.example.usuarios.application.handler.impl;

import com.example.usuarios.application.dto.request.RolRequestDto;
import com.example.usuarios.application.handler.IRolHandler;
import com.example.usuarios.application.mapper.request.IRolRequestMapper;
import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RolHandler implements IRolHandler {

    private final IRolServicePort rolServicePort;
    private final IRolRequestMapper rolRequestMapper;


    @Override
    public Optional<RolEntity> getByRolName(String rolName) {
        return rolServicePort.getByRolName(rolName);
    }

    @Override
    public void saveRol(RolRequestDto rol) {
        rolServicePort.saveRol(rolRequestMapper.toRol(rol));
    }
}
