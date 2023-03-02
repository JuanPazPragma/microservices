package com.example.plaza_comidas.infrastructure.out.jpa.adapter;

import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import com.example.plaza_comidas.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IRestaurantEmployeeEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantEmployeeJpaAdapter implements IRestaurantEmployeePersistencePort {

    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

    @Override
    public RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        RestaurantEmployeeEntity restaurantEmployeeEntity = restaurantEmployeeRepository.save(restaurantEmployeeEntityMapper.toEntity(restaurantEmployeeModel));
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeEntity);
    }

    @Override
    public RestaurantEmployeeModel getRestaurantEmployee(Long restaurantEmployeeId) {
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeRepository.findById(restaurantEmployeeId).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public RestaurantEmployeeModel getRestaurantByEmployeeId(Long employeeId) {
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeRepository.findFirstRestaurantEmployeeByEmployeeId(employeeId));
    }
}
