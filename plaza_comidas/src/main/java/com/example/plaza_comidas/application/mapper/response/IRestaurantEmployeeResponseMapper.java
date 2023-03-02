package com.example.plaza_comidas.application.mapper.response;

import com.example.plaza_comidas.application.dto.response.RestaurantEmployeeResponseDto;
import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeResponseMapper {
    @Mapping(source = "restaurantEmployeeModel.restaurantId.id", target = "restaurantId")
    @Mapping(source = "restaurantEmployeeModel.employeeId.id", target = "employeeId")
    RestaurantEmployeeResponseDto toResponse(RestaurantEmployeeModel restaurantEmployeeModel);
}
