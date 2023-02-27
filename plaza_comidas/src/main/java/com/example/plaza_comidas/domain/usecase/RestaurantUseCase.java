package com.example.plaza_comidas.domain.usecase;

import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }


    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        return restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public RestaurantModel getRestaurant(Long restaurantId) {
        return restaurantPersistencePort.getRestaurant(restaurantId);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(int pageN, int size) {
        return restaurantPersistencePort.getAllRestaurants(pageN, size);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }
}
