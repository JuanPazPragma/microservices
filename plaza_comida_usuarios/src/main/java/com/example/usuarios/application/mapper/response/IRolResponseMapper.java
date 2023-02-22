package com.example.usuarios.application.mapper.response;

import com.example.usuarios.application.dto.response.RolResponseDto;
import com.example.usuarios.domain.model.RolModel;

public interface IRolResponseMapper {
    RolResponseDto toResponse(RolModel rolModel);
}
