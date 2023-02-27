package com.example.plaza_comidas.domain.spi;

import com.example.plaza_comidas.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDish(Long dishId);

    void updateDish(DishModel dishModel);

    List<DishModel> getAllDishesByRestaurant(int pageN, int size, Long restaurantId);
}
