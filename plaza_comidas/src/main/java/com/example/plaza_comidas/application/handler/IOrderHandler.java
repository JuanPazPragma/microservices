package com.example.plaza_comidas.application.handler;

import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderStateResponseDto;
import com.example.plaza_comidas.domain.model.OrderState;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrder(Long orderId);

    List<OrderStateResponseDto> getAllOrdersByOrderState(OrderState orderState);

    OrderResponseDto asignAnOrder(Long orderId);
}
