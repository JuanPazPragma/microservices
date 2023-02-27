package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.dto.request.DishUpdateRequestDto;
import com.example.plaza_comidas.application.dto.request.ListPaginationRequest;
import com.example.plaza_comidas.application.dto.request.UserRequestDto;
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
import com.example.plaza_comidas.infrastructure.configuration.FeignClientInterceptorImp;
import com.example.plaza_comidas.infrastructure.exception.NoUserFoundException;
import com.example.plaza_comidas.infrastructure.exception.NotEnoughPrivileges;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
    private final IUserClient userClient;
    private final JwtHandler jwtHandler;

    @Override
    public DishResponseDto saveDish(DishRequestDto dishRequestDto) {
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(dishRequestDto.getRestaurantId());
        UserRequestDto userRequestDto = userClient.getUserById(restaurantModel.getOwnerId()).getBody().getData();

        if (!Objects.equals(restaurantModel.getOwnerId(), userRequestDto.getId())) {
            throw new NotEnoughPrivileges();
        }

        CategoryModel categoryModel = categoryServicePort.getCategory(dishRequestDto.getCategoryId());
        DishModel dishModel = dishRequestMapper.toDish(dishRequestDto);
        dishModel.setCategoryId(categoryModel);
        dishModel.setRestaurantId(restaurantModel);

        dishServicePort.saveDish(dishModel);

        return dishResponseMapper.toResponse(dishModel, categoryResponseMapper.toResponse(categoryModel), restaurantResponseMapper.toResponse(restaurantModel));
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishes(), categoryServicePort.getAllCategories(), restaurantServicePort.getAllRestaurants());
    }

    @Override
    public List<DishResponseDto> getAllDishesByRestaurant(ListPaginationRequest listPaginationRequest, Long restaurantId) {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishesByRestaurant(listPaginationRequest.getPageN(), listPaginationRequest.getSize(), restaurantId), categoryServicePort.getAllCategories(), restaurantServicePort.getAllRestaurants());
    }

    @Override
    public DishResponseDto getDish(Long dishId) {
        DishModel dishModel = dishServicePort.getDish(dishId);
        return dishResponseMapper.toResponse(dishModel, categoryResponseMapper.toResponse(categoryServicePort.getCategory(dishModel.getCategoryId().getId())),
                restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurant(dishModel.getRestaurantId().getId())));
    }

    @Override
    public DishResponseDto updateDish(DishUpdateRequestDto dishRequestDto) {
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();

        DishModel newDish = dishRequestMapper.toDish(dishRequestDto);
        DishModel oldDish = dishServicePort.getDish(dishRequestDto.getId());

        newDish.setId(oldDish.getId());
        newDish.setName(oldDish.getName());
        newDish.setCategoryId(oldDish.getCategoryId());
        newDish.setDescription(dishRequestDto.getDescription());
        newDish.setPrice(dishRequestDto.getPrice());
        newDish.setRestaurantId(oldDish.getRestaurantId());
        newDish.setUrlImage(oldDish.getUrlImage());
        newDish.setActive(dishRequestDto.isActive());
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(newDish.getRestaurantId().getId());

        if (!userRequestDto.getId().equals(restaurantModel.getOwnerId())) {
            throw new NotEnoughPrivileges();
        }

        dishServicePort.updateDish(newDish);
        CategoryModel categoryModel = categoryServicePort.getCategory(newDish.getCategoryId().getId());


        return dishResponseMapper.toResponse(newDish, categoryResponseMapper.toResponse(categoryModel), restaurantResponseMapper.toResponse(restaurantModel));
    }


}
