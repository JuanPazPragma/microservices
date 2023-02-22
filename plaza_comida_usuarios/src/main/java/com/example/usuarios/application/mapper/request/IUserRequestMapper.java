package com.example.usuarios.application.mapper.request;

import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    @Mapping(source = "userRequestDto.rolId", target = "rolId.id")
    UserModel toUser(UserRequestDto userRequestDto);
    UserResponseDto toDto(UserModel userModel);
    UserRequestDto toUserRequestDto(RegisterRequestDto registerRequestDto);

}
