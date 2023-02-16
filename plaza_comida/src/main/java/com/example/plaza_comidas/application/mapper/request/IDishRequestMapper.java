package com.example.plaza_comidas.application.mapper.request;

import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.dto.request.DishUpdateRequestDto;
import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    @Mapping(source = "dishRequestDto.categoryId", target = "categoryId.id")
    @Mapping(source = "dishRequestDto.restaurantId", target = "restaurantId.id")
    DishModel toDish(DishRequestDto dishRequestDto);

    DishModel toDish(DishUpdateRequestDto dishUpdateRequestDto);

    CategoryModel toCategory(DishRequestDto dishRequestDto);

    RestaurantModel toRestaurant(DishRequestDto dishRequestDto);

}
