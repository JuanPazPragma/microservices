package com.example.plaza_comidas.infrastructure.out.jpa.mapper;

import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    DishEntity toEntity(DishModel dishModel);

    DishModel toDishModel(DishEntity dishEntity);

    List<DishModel> toDishModelList(List<DishEntity> dishEntityList);

}
