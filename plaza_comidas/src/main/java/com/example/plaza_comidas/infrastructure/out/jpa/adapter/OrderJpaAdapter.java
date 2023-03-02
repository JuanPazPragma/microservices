package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.domain.spi.IOrderPersistencePort;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.exception.OrderNotFoundException;
import com.example.plaza_comidas.infrastructure.exception.UserCannotMakeAnOrderException;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.OrderEntity;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return orderEntityMapper.toOrderModel(orderRepository.save(orderEntityMapper.toOrderEntity(orderModel)));
    }

    @Override
    public OrderModel getOrder(Long orderId) {
        return orderEntityMapper.toOrderModel(orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId) {
        return orderEntityMapper.toOrderModelList(orderRepository.findAllByOrderState(orderState, restaurantId));
    }

    @Override
    public Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByClientIdAndOrderStateIn(clientId, orderStateList);

        if (!orderEntityOptional.isEmpty()) {
            throw new UserCannotMakeAnOrderException();
        }

        return true;
    }
}
