package com.example.plaza_comidas.application.handler;

import com.example.plaza_comidas.application.dto.request.ListPaginationRequest;
import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.application.dto.response.AllRestaurantResponseDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    List<AllRestaurantResponseDto> getAllRestaurants(ListPaginationRequest listPaginationRequest);

    List<AllRestaurantResponseDto> getAllRestaurants();

}
