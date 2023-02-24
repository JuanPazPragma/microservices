package com.example.factory;

import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.domain.model.CategoryModel;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;

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

    public static DishRequestDto getDishRequestDto(){
        DishRequestDto dishRequestDto = new DishRequestDto();

        dishRequestDto.setName("Arroz");
        dishRequestDto.setCategoryId(1l);
        dishRequestDto.setDescription("restaurante");
        dishRequestDto.setPrice(200f);
        dishRequestDto.setRestaurantId(1l);
        dishRequestDto.setUrlImage("urlImage");

        return dishRequestDto;
    }

    public static CategoryModel getCategoryModel() {
        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId(1l);
        categoryModel.setName("categoria");
        categoryModel.setDescription("descripcion");

        return categoryModel;
    }

    public static RestaurantModel getRestaurantModel() {
        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId(1l);
        restaurantModel.setName("Corral");
        restaurantModel.setAddress("Calle 5");
        restaurantModel.setOwnerId(4L);
        restaurantModel.setPhoneNumber("+10000");
        restaurantModel.setUrlLogo("logoUrl");
        restaurantModel.setNit("20000");

        return restaurantModel;
    }

}
