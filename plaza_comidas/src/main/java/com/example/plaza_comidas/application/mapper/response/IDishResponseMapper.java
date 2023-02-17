package com.example.plaza_comidas.application.mapper.response;

import com.example.plaza_comidas.application.dto.response.CategoryResponseDto;
import com.example.plaza_comidas.application.dto.response.DishResponseDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryResponseMapper.class, IRestaurantResponseMapper.class})
public interface IDishResponseMapper {

    ICategoryResponseMapper CATEGORY_RESPONSE_MAPPER_INSTANCE = Mappers.getMapper(ICategoryResponseMapper.class);
    IRestaurantResponseMapper RESTAURANT_RESPONSE_MAPPER_INSTANCE = Mappers.getMapper(IRestaurantResponseMapper.class);

    @Mapping(target = "categoryId.name", source = "categoryResponseDto.name")
    @Mapping(target = "categoryId.description", source = "categoryResponseDto.description")
    @Mapping(target = "restaurantId.name", source = "restaurantResponseDto.name")
    @Mapping(target = "name", source = "dishModel.name")
    @Mapping(target = "description", source = "dishModel.description")
    DishResponseDto toResponse(DishModel dishModel, CategoryResponseDto categoryResponseDto, RestaurantResponseDto restaurantResponseDto);

    default List<DishResponseDto> toResponseList(List<DishModel> dishModelList, List<CategoryModel> categoryModelList, List<RestaurantModel> restaurantModelList) {
        return dishModelList.stream()
                .map(dish -> {
                    DishResponseDto dishResponseDto = new DishResponseDto();

                    dishResponseDto.setName(dish.getName());

                    dishResponseDto.setCategoryId(CATEGORY_RESPONSE_MAPPER_INSTANCE.toResponse(categoryModelList.stream().filter(
                            category -> category.getId().equals(dish.getCategoryId())).findFirst().orElse(null)
                    ));

                    dishResponseDto.setDescription(dish.getDescription());
                    dishResponseDto.setPrice(dish.getPrice());

                    dishResponseDto.setRestaurantId(RESTAURANT_RESPONSE_MAPPER_INSTANCE.toResponse(restaurantModelList.stream().filter(
                            restaurant -> restaurant.getId().equals(dish.getRestaurantId())).findFirst().orElse(null)
                    ));

                    dishResponseDto.setUrlImage(dish.getUrlImage());
                    dishResponseDto.setActive(dish.getActive());

                    return dishResponseDto;
                }).toList();
    }

}