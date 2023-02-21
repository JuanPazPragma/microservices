package com.example.usuarios.application.mapper.response;

import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponseDto toResponse(UserModel userModel);
}
