package com.example.plaza_comidas.application.handler;

import com.example.plaza_comidas.application.dto.request.RestaurantEmployeeRequestDto;
import com.example.plaza_comidas.application.dto.response.RestaurantEmployeeResponseDto;

public interface IRestaurantEmployeeHandler {
    RestaurantEmployeeResponseDto saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);

    RestaurantEmployeeResponseDto getRestaurantEmployee(Long restaurantEmployeeId);
}
