package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.domain.spi.IRestaurantPersistencePort;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.exception.RestaurantNotFoundException;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.RestaurantEntity;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<RestaurantModel> getAllRestaurants(int pageN, int size) {
        Pageable pagingSort = PageRequest.of(pageN, size, Sort.by("name"));
        Page<RestaurantEntity> page = restaurantRepository.findAll(pagingSort);
        List<RestaurantEntity> restaurantEntityList = page.getContent();

        if (restaurantEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return restaurantEntityMapper.toRestaurantModelList(restaurantEntityList);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        Iterable<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        return restaurantEntityMapper.toRestaurantModelList((List<RestaurantEntity>) restaurantEntities);
    }
}
