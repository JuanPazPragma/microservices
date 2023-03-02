package com.example.plaza_comidas.domain.spi;

import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;

import java.util.List;

public interface IRestaurantEmployeePersistencePort {
    RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

    RestaurantEmployeeModel getRestaurantEmployee(Long restaurantEmployeeId);

    RestaurantEmployeeModel getRestaurantByEmployeeId(Long employeeId);
}
