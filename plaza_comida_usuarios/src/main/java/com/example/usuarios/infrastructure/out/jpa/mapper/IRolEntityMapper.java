package com.example.usuarios.infrastructure.out.jpa.mapper;

import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolEntityMapper {
    RolEntity toEntity(RolModel rolModel);
    RolModel toRolModel(RolEntity rolEntity);
}
