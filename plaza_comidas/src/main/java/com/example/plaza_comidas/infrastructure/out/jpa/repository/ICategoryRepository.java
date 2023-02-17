package com.example.plaza_comidas.infrastructure.out.jpa.repository;

import com.example.plaza_comidas.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
