package com.example.plaza_comidas.domain.usecase;

import com.example.plaza_comidas.domain.api.ICategoryServicePort;
import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        categoryPersistencePort.saveCategory(categoryModel);
        return categoryModel;
    }

    @Override
    public CategoryModel getCategory(Long categoryId) {
        return categoryPersistencePort.getCategory(categoryId);
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }
}
