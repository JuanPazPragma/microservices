package com.example.plaza_comidas.application.mapper.request;

import com.example.plaza_comidas.application.dto.request.UserRequestDto;
import com.example.plaza_comidas.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    UserModel toUser(UserRequestDto userRequestDto);
}
