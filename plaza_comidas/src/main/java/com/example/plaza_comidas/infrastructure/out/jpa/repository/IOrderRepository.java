package com.example.plaza_comidas.infrastructure.out.jpa.repository;

import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOrderState(OrderState orderState);

    Optional<OrderEntity> findByClientIdAndOrderStateIn(Long clientId, List<OrderState> orderStateList);

}
