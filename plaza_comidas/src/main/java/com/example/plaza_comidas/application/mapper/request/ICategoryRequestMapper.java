package com.example.plaza_comidas.application.mapper.request;

import com.example.plaza_comidas.application.dto.request.CategoryRequestDto;
import com.example.plaza_comidas.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    CategoryModel toCategory(CategoryRequestDto categoryRequestDto);
}
