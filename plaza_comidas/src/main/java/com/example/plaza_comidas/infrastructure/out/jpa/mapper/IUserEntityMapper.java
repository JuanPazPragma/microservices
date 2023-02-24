package com.example.plaza_comidas.infrastructure.out.jpa.mapper;

import com.example.plaza_comidas.domain.model.UserModel;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    UserEntity toEntity(UserModel userModel);

    UserModel toUserModel(UserEntity userEntity);
}
