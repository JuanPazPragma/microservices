package com.example.plaza_comidas.infrastructure.out.jpa.repository;

import com.example.plaza_comidas.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
}
