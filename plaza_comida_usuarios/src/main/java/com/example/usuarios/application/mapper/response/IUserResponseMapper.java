package com.example.usuarios.application.mapper.response;

import com.example.usuarios.application.dto.response.RolResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    @Mapping(target = "rolId.name", source = "rolResponseDto.name")
    @Mapping(target = "rolId.description", source = "rolResponseDto.description")
    @Mapping(target = "name", source = "userModel.name")
    UserResponseDto toResponse(UserModel userModel, RolResponseDto rolResponseDto);
}
