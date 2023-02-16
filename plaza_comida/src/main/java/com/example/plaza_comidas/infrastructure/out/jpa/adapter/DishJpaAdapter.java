package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.spi.IDishPersistencePort;
import com.example.plaza_comidas.infrastructure.exception.DishNotFoundException;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.DishEntity;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public DishModel saveDish(DishModel dishModel) {
        DishModel dish = dishModel;

        dish.setActive(true);

        dishRepository.save(dishEntityMapper.toEntity(dish));
        return dish;
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll();

        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return dishEntityMapper.toDishModelList(dishEntityList);
    }

    @Override
    public DishModel getDish(Long dishId) {
        return dishEntityMapper.toDishModel(dishRepository.findById(dishId).orElseThrow(DishNotFoundException::new));
    }


    @Override
    public void updateDish(DishModel dishModel) {
        dishRepository.save(dishEntityMapper.toEntity(dishModel));
    }
}
