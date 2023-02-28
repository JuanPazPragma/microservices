package com.example.plaza_comidas.application.mapper.response;

import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryResponseMapper.class, IRestaurantResponseMapper.class})
public interface IOrderDishResponseMapper {

    IDishResponseMapper DISH_RESPONSE_MAPPER = Mappers.getMapper(IDishResponseMapper.class);

    @Mapping(source = "orderDishModel.dishId.id", target = "dishId")
    OrderDishResponseDto toResponse(OrderDishModel orderDishModel);

    default List<OrderDishResponseDto> toResponseList(List<OrderDishModel> orderDishModelList) {
        return orderDishModelList.stream()
                .map(
                        orderDish -> {
                            OrderDishResponseDto orderDishResponseDto = new OrderDishResponseDto();

                            orderDishResponseDto.setAmount(orderDish.getAmount());
                            orderDishResponseDto.setDishId(orderDish.getDishId().getId());
                            return orderDishResponseDto;

                        }
                ).toList();
    }
}
