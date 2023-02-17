package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.domain.spi.IRestaurantPersistencePort;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.exception.RestaurantNotFoundException;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.RestaurantEntity;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurantModel));
        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public RestaurantModel getRestaurant(Long restaurantId) {
        return restaurantEntityMapper.toRestaurantModel(restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new));
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> entityList = restaurantRepository.findAll();

        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantModelList(entityList);
    }
}
