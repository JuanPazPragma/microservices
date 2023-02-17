package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.spi.ICategoryPersistencePort;
import com.example.plaza_comidas.infrastructure.exception.CategoryNotFoundException;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.CategoryEntity;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;


    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        CategoryEntity categoryEntity = categoryRepository.save(categoryEntityMapper.toEntity(categoryModel));
        return categoryEntityMapper.toCategoryModel(categoryEntity);
    }

    @Override
    public CategoryModel getCategory(Long categoryId) {
        return categoryEntityMapper.toCategoryModel(categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        if (categoryEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return categoryEntityMapper.toCategoryModelList(categoryEntityList);
    }
}
