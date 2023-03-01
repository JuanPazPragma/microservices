package com.example.plaza_comidas.application.mapper.request;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishRequestMapper {
    @Mapping(source = "orderDishRequest.dishId", target = "dishId.id")
    OrderDishModel toOrderDish(OrderDishRequestDto orderDishRequest);
}
