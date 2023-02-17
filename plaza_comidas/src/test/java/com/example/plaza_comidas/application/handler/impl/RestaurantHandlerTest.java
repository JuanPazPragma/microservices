package com.example.plaza_comidas.application.handler.impl;

import com.example.factory.FactoryRestaurantDataTest;
import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.application.dto.response.ResponseDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.application.mapper.request.IRestaurantRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IRestaurantResponseMapper;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
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

    @Test
    void mustSaveARestaurant() {
        //Given
        RestaurantModel expectedRestaurant = FactoryRestaurantDataTest.getRestaurantModel();

        RestaurantRequestDto restaurantRequestDto = FactoryRestaurantDataTest.getRestaurantRequestDto();

        //When

        when(restaurantRequestMapper.toRestaurant(any())).thenReturn(expectedRestaurant);

        restaurantHandler.saveRestaurant(restaurantRequestDto);

        //Then

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

}