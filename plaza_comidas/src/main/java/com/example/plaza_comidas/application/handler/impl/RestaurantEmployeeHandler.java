package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.RestaurantEmployeeRequestDto;
import com.example.plaza_comidas.application.dto.request.UserRequestDto;
import com.example.plaza_comidas.application.dto.response.RestaurantEmployeeResponseDto;
import com.example.plaza_comidas.application.handler.IRestaurantEmployeeHandler;
import com.example.plaza_comidas.application.mapper.request.IRestaurantEmployeeRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IRestaurantEmployeeResponseMapper;
import com.example.plaza_comidas.domain.api.IRestaurantEmployeeServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.infrastructure.configuration.FeignClientInterceptorImp;
import com.example.plaza_comidas.infrastructure.exception.NotEnoughPrivileges;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantEmployeeHandler implements IRestaurantEmployeeHandler {

    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;
    private final IRestaurantEmployeeResponseMapper restaurantEmployeeResponseMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public RestaurantEmployeeResponseDto saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {

        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(restaurantEmployeeRequestDto.getRestaurantId());
        if (!restaurantModel.getOwnerId().equals(restaurantEmployeeRequestDto.getOwnerId())) {
            throw new NotEnoughPrivileges();
        }

        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeRequestMapper.toRestaurantEmployee(restaurantEmployeeRequestDto);
        return restaurantEmployeeResponseMapper.toResponse(restaurantEmployeeServicePort.saveRestaurantEmployee(restaurantEmployeeModel));
    }

    @Override
    public RestaurantEmployeeResponseDto getRestaurantEmployee(Long restaurantEmployeeId) {
        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeServicePort.getRestaurantEmployee(restaurantEmployeeId);
        return restaurantEmployeeResponseMapper.toResponse(restaurantEmployeeModel);
    }
}
