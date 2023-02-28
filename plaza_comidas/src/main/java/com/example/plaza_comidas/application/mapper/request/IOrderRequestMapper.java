package com.example.plaza_comidas.application.mapper.request;

import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
    @Mapping(source = "orderRequestDto.restaurantId", target = "restaurantId.id")
    OrderModel toOrderModel(OrderRequestDto orderRequestDto);
}
