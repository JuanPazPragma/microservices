package com.example.plaza_comidas.application.handler.impl;

import com.example.factory.FactoryDishDataTest;
import com.example.factory.FactoryRestaurantDataTest;
import com.example.plaza_comidas.application.dto.request.DishRequestDto;
import com.example.plaza_comidas.application.dto.request.DishUpdateRequestDto;
import com.example.plaza_comidas.application.dto.request.ListPaginationRequest;
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
import com.example.plaza_comidas.infrastructure.configuration.FeignClientInterceptorImp;
import com.example.plaza_comidas.infrastructure.exception.NotEnoughPrivileges;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
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
    @Mock
    JwtHandler jwtHandler;

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
    void throwNotEnoughPrivilegesWhereGetUserIsNotEqualsToOwnerId() {
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

    @Test
    void mustUpdateADish() {
        DishModel oldDish = FactoryDishDataTest.getDishModle();
        DishModel newDish = FactoryDishDataTest.getDishModel2();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        CategoryResponseDto categoryResponseDto = FactoryDishDataTest.getCategoryResponseDto();
        RestaurantResponseDto restaurantResponseDto = FactoryDishDataTest.getRestaurantResponseDto();
        DishResponseDto dishResponseDto = FactoryDishDataTest.getDishUpdateResponseDto();
        DishUpdateRequestDto dishUpdateRequestDto = FactoryDishDataTest.getDishUpdateRequest();
        RestaurantModel restaurantModel = FactoryDishDataTest.getRestaurantModel();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(dishServicePort.getDish(any())).thenReturn(oldDish);
            when(restaurantServicePort.getRestaurant(any())).thenReturn(newDish.getRestaurantId());
            when(dishRequestMapper.toDish(any(DishUpdateRequestDto.class))).thenReturn(newDish);
            when(categoryResponseMapper.toResponse(any())).thenReturn(categoryResponseDto);
            when(restaurantResponseMapper.toResponse(any())).thenReturn(restaurantResponseDto);
            when(dishResponseMapper.toResponse(any(), any(), any())).thenReturn(dishResponseDto);

            Assertions.assertNotEquals(restaurantModel.getOwnerId(), 2L);
            Assertions.assertEquals(dishResponseDto, dishHandler.updateDish(dishUpdateRequestDto));

            verify(dishServicePort).updateDish(any(DishModel.class));
        }
    }

    @Test
    void throwNullPointerExceptionWhereUserRequestIsNull() {
        DishUpdateRequestDto dishUpdateRequestDto = FactoryDishDataTest.getDishUpdateRequest();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(null);

            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> dishHandler.updateDish(dishUpdateRequestDto)
            );
        }
    }

    @Test
    void throwNoEnoughPrivilegesWhereUserIdIsNotTheOwnerId() {
        DishModel oldDish = FactoryDishDataTest.getDishModle();
        DishModel newDish = FactoryDishDataTest.getDishModel2();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        DishUpdateRequestDto dishUpdateRequestDto = FactoryDishDataTest.getDishUpdateRequest();
        RestaurantModel restaurantModel = FactoryDishDataTest.getRestaurantModel2();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(dishServicePort.getDish(any())).thenReturn(oldDish);
            when(restaurantServicePort.getRestaurant(any())).thenReturn(newDish.getRestaurantId());
            when(dishRequestMapper.toDish(any(DishUpdateRequestDto.class))).thenReturn(newDish);
            when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);

            Assertions.assertThrows(
                    NotEnoughPrivileges.class,
                    () -> dishHandler.updateDish(dishUpdateRequestDto)
            );

        }
    }

    @Test
    void mustGetAllDishesByRestaurantId() {
        List<DishModel> dishModelList = new ArrayList<>();
        dishModelList.add(FactoryDishDataTest.getDishModle());

        List<CategoryModel> categoryModelList = new ArrayList<>();
        categoryModelList.add(FactoryDishDataTest.getCategoryModel());

        List<RestaurantModel> restaurantModelList = new ArrayList<>();
        restaurantModelList.add(FactoryDishDataTest.getRestaurantModel());

        List<DishResponseDto> dishResponseDtos = new ArrayList<>();
        dishResponseDtos.add(FactoryDishDataTest.getDishResponseDto());

        ListPaginationRequest listPaginationRequest = new ListPaginationRequest();
        listPaginationRequest.setPageN(0);
        listPaginationRequest.setSize(20);

        when(dishServicePort.getAllDishesByRestaurant(0, 20, 1L)).thenReturn(dishModelList);
        when(categoryServicePort.getAllCategories()).thenReturn(categoryModelList);
        when(restaurantServicePort.getAllRestaurants()).thenReturn(restaurantModelList);

        Assertions.assertEquals(dishResponseDtos, dishHandler.getAllDishesByRestaurant(listPaginationRequest, 1L));

        verify(dishResponseMapper).toResponseList(dishModelList, categoryModelList, restaurantModelList);
    }

}