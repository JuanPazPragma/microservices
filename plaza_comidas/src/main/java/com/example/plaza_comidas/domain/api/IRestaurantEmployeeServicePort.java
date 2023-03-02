package com.example.plaza_comidas.domain.api;

import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;

import java.util.List;

public interface IRestaurantEmployeeServicePort {
    RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

    RestaurantEmployeeModel getRestaurantEmployee(Long restaurantEmployeeId);

    RestaurantEmployeeModel getRestaurantByEmployeeId(Long employeeId);
}
