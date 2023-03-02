package com.example.plaza_comidas.infrastructure.out.jpa.repository;

import com.example.plaza_comidas.infrastructure.out.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {

    @Query("SELECT o FROM OrderDishEntity o WHERE o.orderId.id = :orderId")
    List<OrderDishEntity> findAllByOrderId(Long orderId);

}
