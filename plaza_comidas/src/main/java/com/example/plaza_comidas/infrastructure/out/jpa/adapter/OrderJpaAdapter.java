package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.domain.spi.IOrderPersistencePort;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
        return orderEntityMapper.toOrderModel(orderRepository.findById(orderId).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<OrderModel> getAllOrdersByOrderState(OrderState orderState) {
        return null;
    }
}
