package com.example.factory;

import com.example.plaza_comidas.application.dto.request.RestaurantRequestDto;
import com.example.plaza_comidas.domain.model.RestaurantModel;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FactoryRestaurantDataTest {
    public static RestaurantModel getRestaurantModel() {
        RestaurantModel expectedRestaurant = new RestaurantModel();

        expectedRestaurant.setName("Corral");
        expectedRestaurant.setAddress("Calle 5");
        expectedRestaurant.setOwnerId("4");
        expectedRestaurant.setPhoneNumber("+10000");
        expectedRestaurant.setUrlLogo("logoUrl");
        expectedRestaurant.setNit("20000");

        return expectedRestaurant;
    }

    public static RestaurantRequestDto getRestaurantRequestDto() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("Corral");
        restaurantRequestDto.setAddress("Calle 5");
        restaurantRequestDto.setOwnerId("4");
        restaurantRequestDto.setPhoneNumber("+10000");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantWithoutName() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setAddress("Calle 5");
        restaurantRequestDto.setOwnerId("4");
        restaurantRequestDto.setPhoneNumber("+10000");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantBadPhoneNumber() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("Corral");
        restaurantRequestDto.setAddress("Calle 5");
        restaurantRequestDto.setOwnerId("4");
        restaurantRequestDto.setPhoneNumber("Telefono");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantInvalidName() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("123546");
        restaurantRequestDto.setAddress("Calle 5");
        restaurantRequestDto.setOwnerId("4");
        restaurantRequestDto.setPhoneNumber("+90000");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }
}
