package com.example.plaza_comidas.infrastructure.out.jpa.mapper;

import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    CategoryEntity toEntity(CategoryModel categoryModel);

    CategoryModel toCategoryModel(CategoryEntity categoryEntity);

    List<CategoryModel> toCategoryModelList(List<CategoryEntity> categoryEntityList);

}
