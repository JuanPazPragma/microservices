package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.dto.request.DishUpdateRequestDto;
import com.example.plaza_comidas.application.dto.response.DishResponseDto;
import com.example.plaza_comidas.application.handler.IDishHandler;
import com.example.plaza_comidas.application.mapper.request.IDishRequestMapper;
import com.example.plaza_comidas.application.mapper.response.ICategoryResponseMapper;
import com.example.plaza_comidas.application.mapper.response.IDishResponseMapper;
import com.example.plaza_comidas.application.mapper.response.IRestaurantResponseMapper;
import com.example.plaza_comidas.domain.api.ICategoryServicePort;
import com.example.plaza_comidas.domain.api.IDishServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(dishRequestDto.getCategoryId());

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(dishRequestDto.getRestaurantId());

        DishModel dishModel = dishRequestMapper.toDish(dishRequestDto);
        dishModel.setCategoryId(categoryModel);
        dishModel.setRestaurantId(restaurantModel);

        dishServicePort.saveDish(dishModel);
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishes(), categoryServicePort.getAllCategories(), restaurantServicePort.getAllRestaurants());
    }

    @Override
    public DishResponseDto getDish(Long dishId) {
        DishModel dishModel = dishServicePort.getDish(dishId);
        return dishResponseMapper.toResponse(dishModel, categoryResponseMapper.toResponse(categoryServicePort.getCategory(dishModel.getCategoryId().getId())),
                restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurant(dishModel.getRestaurantId().getId())));
    }

    @Override
    public void updateDish(DishUpdateRequestDto dishRequestDto) {
        DishModel oldDish = dishServicePort.getDish(dishRequestDto.getId());

        DishModel newDish = dishRequestMapper.toDish(dishRequestDto);
        newDish.setId(oldDish.getId());
        newDish.setName(oldDish.getName());
        newDish.setCategoryId(oldDish.getCategoryId());
        newDish.setDescription(dishRequestDto.getDescription());
        newDish.setPrice(dishRequestDto.getPrice());
        newDish.setRestaurantId(oldDish.getRestaurantId());
        newDish.setUrlImage(oldDish.getUrlImage());
        newDish.setActive(oldDish.getActive());

        dishServicePort.updateDish(newDish);

    }
}
