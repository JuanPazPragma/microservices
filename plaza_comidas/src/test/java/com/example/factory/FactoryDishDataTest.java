package com.example.factory;

import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.dto.response.CategoryResponseDto;
import com.example.plaza_comidas.application.dto.response.DishResponseDto;
import com.example.plaza_comidas.application.dto.response.ResponseClientDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.factory.FactoryRestaurantDataTest.getResponseClientDto;

public class FactoryDishDataTest {

    public static DishModel getDishModle() {
        DishModel expectedDishModel = new DishModel();

        expectedDishModel.setName("Arroz");
        expectedDishModel.setCategoryId(getCategoryModel());
        expectedDishModel.setDescription("restaurante");
        expectedDishModel.setPrice(200f);
        expectedDishModel.setRestaurantId(getRestaurantModel());
        expectedDishModel.setUrlImage("urlImage");
        expectedDishModel.setActive(true);

        return expectedDishModel;
    }

    public static DishRequestDto getDishRequestDto() {
        DishRequestDto dishRequestDto = new DishRequestDto();

        dishRequestDto.setName("Arroz");
        dishRequestDto.setCategoryId(1L);
        dishRequestDto.setDescription("restaurante");
        dishRequestDto.setPrice(200f);
        dishRequestDto.setRestaurantId(1L);
        dishRequestDto.setUrlImage("urlImage");

        return dishRequestDto;
    }

    public static CategoryModel getCategoryModel() {
        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId(1L);
        categoryModel.setName("categoria");
        categoryModel.setDescription("descripcion");

        return categoryModel;
    }

    public static RestaurantModel getRestaurantModel() {
        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId(1L);
        restaurantModel.setName("Corral");
        restaurantModel.setAddress("Calle 5");
        restaurantModel.setOwnerId(1L);
        restaurantModel.setPhoneNumber("+10000");
        restaurantModel.setUrlLogo("logoUrl");
        restaurantModel.setNit("20000");

        return restaurantModel;
    }

    public static DishResponseDto getDishResponseDto() {
        DishResponseDto dishResponseDto = new DishResponseDto();

        dishResponseDto.setName("Arroz");
        dishResponseDto.setCategoryId(getCategoryResponseDto());
        dishResponseDto.setDescription("restaurante");
        dishResponseDto.setPrice(200f);
        dishResponseDto.setRestaurantId(getRestaurantResponseDto());
        dishResponseDto.setUrlImage("urlImage");
        dishResponseDto.setActive(true);

        return dishResponseDto;
    }

    public static CategoryResponseDto getCategoryResponseDto() {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName("categoria");
        categoryResponseDto.setDescription("descripcion");
        return categoryResponseDto;
    }


    public static RestaurantResponseDto getRestaurantResponseDto() {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setName("Corral");
        restaurantResponseDto.setAddress("Calle 5");
        restaurantResponseDto.setOwnerId(4L);
        restaurantResponseDto.setPhoneNumber("+10000");
        restaurantResponseDto.setUrlLogo("logoUrl");
        restaurantResponseDto.setNit("20000");

        return restaurantResponseDto;
    }

    public static RestaurantModel getRestaurantModelIncorrectId() {
        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId(1L);
        restaurantModel.setName("Corral");
        restaurantModel.setAddress("Calle 5");
        restaurantModel.setOwnerId(4L);
        restaurantModel.setPhoneNumber("+10000");
        restaurantModel.setUrlLogo("logoUrl");
        restaurantModel.setNit("20000");

        return restaurantModel;
    }

}
