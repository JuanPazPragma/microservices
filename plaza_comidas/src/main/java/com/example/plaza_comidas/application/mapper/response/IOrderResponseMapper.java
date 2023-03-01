package com.example.plaza_comidas.application.mapper.response;

import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.domain.model.OrderDishModel;
import com.example.plaza_comidas.domain.model.OrderModel;
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
public interface IOrderResponseMapper {

    IRestaurantResponseMapper RESTAURANT_RESPONSE_MAPPER_INSTANCE = Mappers.getMapper(IRestaurantResponseMapper.class);
    IOrderDishResponseMapper ORDER_DISH_RESPONSE_MAPPER = Mappers.getMapper(IOrderDishResponseMapper.class);

    @Mapping(source = "orderDishResponseDtoList", target = "orders")
    @Mapping(source = "orderModel.id", target = "orderId")
    OrderResponseDto toResponse(OrderModel orderModel, List<OrderDishResponseDto> orderDishResponseDtoList);

    default List<OrderResponseDto> toReponseList(List<OrderModel> orderModelList, List<RestaurantModel> restaurantModelList, List<OrderDishModel> orderDishModelList) {
        return orderModelList.stream()
                .map(order -> {
                    OrderResponseDto orderResponseDto = new OrderResponseDto();

                    orderResponseDto.setOrderId(order.getId());
                    orderResponseDto.setDate(order.getDate());
                    orderResponseDto.setOrderState(order.getOrderState());

                    orderResponseDto.setRestaurantId(RESTAURANT_RESPONSE_MAPPER_INSTANCE.toResponse(restaurantModelList.stream().filter(
                            restaurant -> restaurant.getId().equals(order.getRestaurantId().getId())
                    ).findFirst().get()));
                    

                    return orderResponseDto;
                }).toList();
    }
}
