package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.handler.IOrderDishHandler;
import com.example.plaza_comidas.application.handler.IOrderHandler;
import com.example.plaza_comidas.application.mapper.request.IOrderRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IOrderResponseMapper;
import com.example.plaza_comidas.domain.api.IOrderServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderDishHandler orderDishHandler;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        List<OrderDishRequestDto> orderDishRequestDtoList = orderRequestDto.getOrders();
        List<OrderDishResponseDto> orderDishResponseDtoList =
                orderDishRequestDtoList.stream()
                        .map(orderDishHandler::createOrderDish
                        ).toList();

        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(orderRequestDto.getRestaurantId());

        OrderModel orderModel = new OrderModel();
        orderModel.setClientId(null);
        orderModel.setDate(null);
        orderModel.setOrderState(OrderState.PENDIENTE);
        orderModel.setChefId(null);
        orderModel.setRestaurantId(restaurantModel);

        orderServicePort.createOrder(orderModel);

        return orderResponseMapper.toResponse(orderModel, orderDishResponseDtoList);
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByOrderState(OrderState orderState) {
        return null;
    }
}
