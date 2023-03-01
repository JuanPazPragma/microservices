package com.example.plaza_comidas.application.handler;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;

import java.util.List;

public interface IOrderDishHandler {
    OrderDishResponseDto createOrderDish(OrderDishRequestDto orderDishRequestDto, Long orderId);

    OrderDishResponseDto getOrderDish(Long orderDishId);

    List<OrderDishResponseDto> getAllOrderDishByOrder(Long orderId);
}
