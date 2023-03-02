package com.example.plaza_comidas.infrastructure.out.jpa.repository;

import com.example.plaza_comidas.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRestaurantEmployeeRepository extends JpaRepository<RestaurantEmployeeEntity, Long> {

    RestaurantEmployeeEntity findFirstRestaurantEmployeeByEmployeeId(Long employeeId);

}
