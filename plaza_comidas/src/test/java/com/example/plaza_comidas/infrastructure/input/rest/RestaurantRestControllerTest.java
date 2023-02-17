package com.example.plaza_comidas.infrastructure.input.rest;

import com.example.plaza_comidas.application.handler.IRestaurantHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RestaurantRestControllerTest {

    @InjectMocks
    IRestaurantHandler restaurantHandler;

    @Test
    void mustSaveRestaurant() {


    }


}