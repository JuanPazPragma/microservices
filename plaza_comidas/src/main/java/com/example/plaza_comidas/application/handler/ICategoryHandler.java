package com.example.plaza_comidas.application.handler;

import com.example.plaza_comidas.application.dto.request.CategoryRequestDto;
import com.example.plaza_comidas.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();
}
