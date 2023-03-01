package com.example.plaza_comidas.domain.usecase;

import com.example.plaza_comidas.domain.api.IOrderDishServicePort;
import com.example.plaza_comidas.domain.model.OrderDishModel;
import com.example.plaza_comidas.domain.spi.IOrderDishPersistencePort;

import java.util.List;

public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }

    @Override
    public OrderDishModel createOrderDish(OrderDishModel orderDishModel) {
        return orderDishPersistencePort.createOrderDish(orderDishModel);
    }

    @Override
    public OrderDishModel getOrderDish(Long orderDishId) {
        return orderDishPersistencePort.getOrderDish(orderDishId);
    }

    @Override
    public List<OrderDishModel> getAllOrderDish() {
        return orderDishPersistencePort.getAllOrderDish();
    }

    @Override
    public List<OrderDishModel> getAllOrderDishByOrder(Long orderId) {
        return orderDishPersistencePort.getAllOrderDishByOrder(orderId);
    }
}
