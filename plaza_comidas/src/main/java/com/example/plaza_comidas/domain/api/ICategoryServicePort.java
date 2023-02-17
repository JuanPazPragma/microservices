package com.example.plaza_comidas.domain.api;

import com.example.plaza_comidas.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {
    CategoryModel saveCategory(CategoryModel categoryModel);

    CategoryModel getCategory(Long categoryId);

    List<CategoryModel> getAllCategories();

}
