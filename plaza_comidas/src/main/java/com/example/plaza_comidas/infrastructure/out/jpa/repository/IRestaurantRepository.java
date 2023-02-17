package com.example.plaza_comidas.infrastructure.out.jpa.repository;

import com.example.plaza_comidas.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
