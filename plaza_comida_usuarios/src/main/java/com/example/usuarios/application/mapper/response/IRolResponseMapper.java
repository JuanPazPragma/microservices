package com.example.usuarios.application.mapper.response;

import com.example.usuarios.application.dto.response.RolResponseDto;
import com.example.usuarios.domain.model.RolModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolResponseMapper {
    RolResponseDto toResponse(RolModel rolModel);
}
