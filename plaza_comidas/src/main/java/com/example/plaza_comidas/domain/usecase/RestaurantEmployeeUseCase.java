package com.example.plaza_comidas.domain.usecase;

import com.example.plaza_comidas.domain.api.IRestaurantEmployeeServicePort;
import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import com.example.plaza_comidas.domain.spi.IOrderPersistencePort;
import com.example.plaza_comidas.domain.spi.IRestaurantEmployeePersistencePort;

import java.util.List;

public class RestaurantEmployeeUseCase implements IRestaurantEmployeeServicePort {

    private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

    public RestaurantEmployeeUseCase(IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort) {
        this.restaurantEmployeePersistencePort = restaurantEmployeePersistencePort;
    }

    @Override
    public RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        return restaurantEmployeePersistencePort.saveRestaurantEmployee(restaurantEmployeeModel);
    }

    @Override
    public RestaurantEmployeeModel getRestaurantEmployee(Long restaurantEmployeeId) {
        return restaurantEmployeePersistencePort.getRestaurantEmployee(restaurantEmployeeId);
    }

    @Override
    public RestaurantEmployeeModel getRestaurantByEmployeeId(Long employeeId) {
        return restaurantEmployeePersistencePort.getRestaurantByEmployeeId(employeeId);
    }
}
