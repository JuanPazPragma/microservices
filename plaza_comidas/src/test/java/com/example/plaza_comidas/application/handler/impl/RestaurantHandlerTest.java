package com.example.plaza_comidas.application.handler.impl;

import com.example.factory.FactoryRestaurantDataTest;
import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.application.dto.request.UserRequestDto;
import com.example.plaza_comidas.application.dto.response.ResponseClientDto;
import com.example.plaza_comidas.application.dto.response.ResponseDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.application.mapper.request.IRestaurantRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IRestaurantResponseMapper;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RestaurantHandlerTest {
    @InjectMocks
    RestaurantHandler restaurantHandler;
    @Mock
    IRestaurantServicePort restaurantServicePort;
    @Mock
    IRestaurantRequestMapper restaurantRequestMapper;
    @Mock
    IRestaurantResponseMapper restaurantResponseMapper;
    @Mock
    IUserClient userClient;

    @Test
    void mustSaveARestaurant() {
        RestaurantModel expectedRestaurant = FactoryRestaurantDataTest.getRestaurantModel();

        RestaurantRequestDto restaurantRequestDto = FactoryRestaurantDataTest.getRestaurantRequestDto();

        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();

        RestaurantResponseDto restaurantResponseDto = FactoryRestaurantDataTest.getRestaurantResponseDto();

        RestaurantModel restaurantModel = FactoryRestaurantDataTest.getRestaurantModel();

        when(restaurantRequestMapper.toRestaurant(any())).thenReturn(expectedRestaurant);
        when(userClient.getUserById(any())).thenReturn(response);
        when(restaurantResponseMapper.toResponse(any())).thenReturn(restaurantResponseDto);
        when(restaurantServicePort.saveRestaurant(any())).thenReturn(restaurantModel);
        when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);

        Assertions.assertEquals(restaurantResponseDto, restaurantHandler.saveRestaurant(restaurantRequestDto));

        verify(restaurantServicePort).saveRestaurant(any(RestaurantModel.class));
    }

    @Test
    void invalidPhoneFormat() {
        Validator validator = FactoryRestaurantDataTest.getValidator();

        RestaurantRequestDto restaurantInvalidPhoneFormat = FactoryRestaurantDataTest.getRestaurantBadPhoneNumber();

        Set<ConstraintViolation<RestaurantRequestDto>> violations = validator.validate(restaurantInvalidPhoneFormat);

        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidRequestNameMustBeNotNull() {
        Validator validator = FactoryRestaurantDataTest.getValidator();

        RestaurantRequestDto restaurantWithoutName = FactoryRestaurantDataTest.getRestaurantWithoutName();

        Set<ConstraintViolation<RestaurantRequestDto>> violations = validator.validate(restaurantWithoutName);

        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidRequestNameAllNumericName() {
        Validator validator = FactoryRestaurantDataTest.getValidator();

        RestaurantRequestDto restaurantWhitAllNumberName = FactoryRestaurantDataTest.getRestaurantInvalidName();

        Set<ConstraintViolation<RestaurantRequestDto>> violations = validator.validate(restaurantWhitAllNumberName);

        assertFalse(violations.isEmpty());
    }

    @Test
    void throwNoDataFoundExceptionWhereGetUserIsNull() {
        RestaurantModel expectedRestaurant = FactoryRestaurantDataTest.getRestaurantModel();
        RestaurantRequestDto restaurantRequestDto = FactoryRestaurantDataTest.getRestaurantRequestDto();

        when(restaurantRequestMapper.toRestaurant(any())).thenReturn(expectedRestaurant);
        when(userClient.getUserById(any())).thenReturn(null);

        Assertions.assertThrows(
                NullPointerException.class,
                () -> restaurantHandler.saveRestaurant(restaurantRequestDto)
        );
    }

}