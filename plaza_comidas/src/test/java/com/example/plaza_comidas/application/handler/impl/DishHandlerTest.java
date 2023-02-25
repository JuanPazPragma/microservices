package com.example.plaza_comidas.application.handler.impl;

import com.example.factory.FactoryDishDataTest;
import com.example.factory.FactoryRestaurantDataTest;
import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.application.dto.response.CategoryResponseDto;
import com.example.plaza_comidas.application.dto.response.DishResponseDto;
import com.example.plaza_comidas.application.dto.response.ResponseClientDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
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
import com.example.plaza_comidas.infrastructure.exception.NotEnoughPrivileges;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DishHandlerTest {
    @InjectMocks
    DishHandler dishHandler;
    @Mock
    IDishServicePort dishServicePort;
    @Mock
    IDishRequestMapper dishRequestMapper;
    @Mock
    IDishResponseMapper dishResponseMapper;
    @Mock
    ICategoryServicePort categoryServicePort;
    @Mock
    ICategoryResponseMapper categoryResponseMapper;
    @Mock
    IRestaurantServicePort restaurantServicePort;
    @Mock
    IRestaurantResponseMapper restaurantResponseMapper;
    @Mock
    IUserClient userClient;

    @Test
    void mustSaveADish() {
        DishRequestDto dishRequestDto = FactoryDishDataTest.getDishRequestDto();
        DishModel dishModel = FactoryDishDataTest.getDishModle();
        DishResponseDto dishResponseDto = FactoryDishDataTest.getDishResponseDto();
        CategoryModel categoryModel = FactoryDishDataTest.getCategoryModel();
        RestaurantModel restaurantModel = FactoryDishDataTest.getRestaurantModel();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        CategoryResponseDto categoryResponseDto = FactoryDishDataTest.getCategoryResponseDto();
        RestaurantResponseDto restaurantResponseDto = FactoryDishDataTest.getRestaurantResponseDto();

        when(userClient.getUserById(any())).thenReturn(response);
        when(categoryServicePort.getCategory(any())).thenReturn(categoryModel);
        when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);
        when(dishRequestMapper.toDish(dishRequestDto)).thenReturn(dishModel);
        when(categoryResponseMapper.toResponse(any())).thenReturn(categoryResponseDto);
        when(restaurantResponseMapper.toResponse(any())).thenReturn(restaurantResponseDto);
        when(dishResponseMapper.toResponse(any(), any(), any())).thenReturn(dishResponseDto);

        Assertions.assertEquals(dishResponseDto, dishHandler.saveDish(dishRequestDto));

        verify(dishServicePort).saveDish(any(DishModel.class));
    }

    @Test
    void throwNotEnoughPrivilegesWhereGetUserIsNorEqualsToOwnerId() {
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        RestaurantModel restaurantModelIncorrectId = FactoryDishDataTest.getRestaurantModelIncorrectId();
        DishRequestDto dishRequestDto = FactoryDishDataTest.getDishRequestDto();

        when(userClient.getUserById(any())).thenReturn(response);
        when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModelIncorrectId);

        Assertions.assertThrows(
                NotEnoughPrivileges.class,
                () -> dishHandler.saveDish(dishRequestDto)
        );
    }

}