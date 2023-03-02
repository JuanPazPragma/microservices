package com.example.plaza_comidas.application.mapper.request;

import com.example.plaza_comidas.application.dto.request.RestaurantEmployeeRequestDto;
import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeRequestMapper {
    @Mapping(source = "restaurantEmployeeRequestDto.restaurantId", target = "restaurantId.id")
    @Mapping(source = "restaurantEmployeeRequestDto.employeeId", target = "employeeId.id")
    RestaurantEmployeeModel toRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);
}
