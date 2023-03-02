package com.example.plaza_comidas.domain.spi;

import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    OrderModel createOrder(OrderModel orderModel);

    OrderModel getOrder(Long orderId);

    List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId);

    Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList);
}
