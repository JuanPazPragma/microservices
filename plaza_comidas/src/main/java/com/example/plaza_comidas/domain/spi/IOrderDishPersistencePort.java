package com.example.plaza_comidas.domain.spi;

import com.example.plaza_comidas.domain.model.OrderDishModel;

import java.util.List;

public interface IOrderDishPersistencePort {
    OrderDishModel createOrderDish(OrderDishModel orderDishModel);

    OrderDishModel getOrderDish(Long orderDishId);
    List<OrderDishModel> getAllOrderDish();

    List<OrderDishModel> getAllOrderDishByOrder(Long orderId);
}
