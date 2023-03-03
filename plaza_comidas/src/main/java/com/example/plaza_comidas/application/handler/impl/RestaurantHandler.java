package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.ListPaginationRequest;
import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.application.dto.request.UserRequestDto;
import com.example.plaza_comidas.application.dto.response.AllRestaurantResponseDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.application.handler.IRestaurantHandler;
import com.example.plaza_comidas.application.mapper.request.IRestaurantRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IRestaurantResponseMapper;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.infrastructure.exception.NoUserFoundException;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    private final IUserClient userClient;

    @Override
    public RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        UserRequestDto userRequestDto = userClient.getUserById(restaurantModel.getOwnerId()).getBody().getData();

        restaurantModel.setOwnerId(restaurantRequestDto.getOwnerId());
        RestaurantModel restaurant = restaurantServicePort.saveRestaurant(restaurantModel);
        return restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurant(restaurant.getId()));
    }

    @Override
    public List<AllRestaurantResponseDto> getAllRestaurants(ListPaginationRequest listPaginationRequest) {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants(listPaginationRequest.getPageN(), listPaginationRequest.getSize()));
    }

    @Override
    public List<AllRestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }
}
