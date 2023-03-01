package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.handler.IOrderDishHandler;
import com.example.plaza_comidas.application.mapper.request.IOrderDishRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IOrderDishResponseMapper;
import com.example.plaza_comidas.domain.api.IDishServicePort;
import com.example.plaza_comidas.domain.api.IOrderDishServicePort;
import com.example.plaza_comidas.domain.api.IOrderServicePort;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.OrderDishModel;
import com.example.plaza_comidas.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDishHandler implements IOrderDishHandler {

    private final IOrderDishServicePort orderDishServicePort;
    private final IOrderDishRequestMapper orderDishRequestMapper;
    private final IOrderDishResponseMapper orderDishResponseMapper;
    private final IDishServicePort dishServicePort;
    private final IOrderServicePort orderServicePort;

    @Override
    public OrderDishResponseDto createOrderDish(OrderDishRequestDto orderDishRequestDto, Long orderId) {
        DishModel dishModel = dishServicePort.getDish(orderDishRequestDto.getDishId());
        OrderModel orderModel = orderServicePort.getOrder(orderId);

        OrderDishModel orderDishModel = orderDishRequestMapper.toOrderDish(orderDishRequestDto);
        orderDishModel.setDishId(dishModel);
        orderDishModel.setOrderId(orderModel);

        orderDishServicePort.createOrderDish(orderDishModel);

        return orderDishResponseMapper.toResponse(orderDishModel);
    }

    @Override
    public OrderDishResponseDto getOrderDish(Long orderDishId) {
        OrderDishModel orderDishModel = orderDishServicePort.getOrderDish(orderDishId);
        return orderDishResponseMapper.toResponse(orderDishModel);
    }

    @Override
    public List<OrderDishResponseDto> getAllOrderDishByOrder(Long orderId) {
        return orderDishResponseMapper.toResponseList(orderDishServicePort.getAllOrderDishByOrder(orderId));
    }
}
