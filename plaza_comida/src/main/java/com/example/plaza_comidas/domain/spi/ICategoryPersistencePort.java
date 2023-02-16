package com.example.plaza_comidas.domain.spi;

import com.example.plaza_comidas.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryPersistencePort {

    CategoryModel saveCategory(CategoryModel categoryModel);
    CategoryModel getCategory(Long categoryId);
    List<CategoryModel> getAllCategories();

}
