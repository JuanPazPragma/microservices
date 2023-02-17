package com.example.plaza_comidas.application.handler.impl;

import com.example.factory.FactoryDishDataTest;
import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.mapper.request.IDishRequestMapper;
import com.example.plaza_comidas.domain.api.IDishServicePort;
import com.example.plaza_comidas.domain.model.DishModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    void mustSaveADish() {

        DishModel expectedDish = FactoryDishDataTest.getDishModle();

        DishRequestDto dishRequestDto = FactoryDishDataTest.getDishRequestDto();

        when(dishRequestMapper.toDish(any(DishRequestDto.class))).thenReturn(expectedDish);

        dishHandler.saveDish(dishRequestDto);

        verify(dishServicePort).saveDish(any(DishModel.class));
    }
}