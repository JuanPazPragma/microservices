package com.example.plaza_comidas.domain.usecase;

import com.example.plaza_comidas.domain.api.IOrderServicePort;
import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUserCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUserCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return orderPersistencePort.createOrder(orderModel);
    }

    @Override
    public OrderModel getOrder(Long orderId) {
        return orderPersistencePort.getOrder(orderId);
    }

    @Override
    public List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId) {
        return orderPersistencePort.getAllOrdersByOrderState(orderState, restaurantId);
    }

    @Override
    public Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList) {
        return orderPersistencePort.getAllOrdersByUserIdOrderStateIn(clientId, orderStateList);
    }
}
