package com.example.plaza_comidas.application.handler.impl;

import com.example.factory.FactoryDishDataTest;
import com.example.factory.FactoryOrderDataTest;
import com.example.factory.FactoryRestaurantDataTest;
import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderStateResponseDto;
import com.example.plaza_comidas.application.dto.response.ResponseClientDto;
import com.example.plaza_comidas.application.handler.IOrderDishHandler;
import com.example.plaza_comidas.application.mapper.request.IUserRequestMapper;
import com.example.plaza_comidas.application.mapper.response.IOrderDishResponseMapper;
import com.example.plaza_comidas.application.mapper.response.IOrderResponseMapper;
import com.example.plaza_comidas.domain.api.IDishServicePort;
import com.example.plaza_comidas.domain.api.IOrderDishServicePort;
import com.example.plaza_comidas.domain.api.IOrderServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantEmployeeServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.model.DishModel;
import com.example.plaza_comidas.domain.model.OrderDishModel;
import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.domain.model.RestaurantEmployeeModel;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.domain.model.UserModel;
import com.example.plaza_comidas.infrastructure.configuration.FeignClientInterceptorImp;
import com.example.plaza_comidas.infrastructure.exception.DishNotFoundInRestaurantException;
import com.example.plaza_comidas.infrastructure.exception.NotEnoughPrivileges;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderHandlerTest {
    @InjectMocks
    OrderHandler orderHandler;

    @Mock
    IRestaurantServicePort restaurantServicePort;
    @Mock
    JwtHandler jwtHandler;
    @Mock
    IUserClient userClient;
    @Mock
    IOrderServicePort orderServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IOrderDishHandler orderDishHandler;
    @Mock
    IOrderResponseMapper orderResponseMapper;
    @Mock
    IOrderDishServicePort orderDishServicePort;
    @Mock
    IOrderDishResponseMapper orderDishResponseMapper;
    @Mock
    IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    @Mock
    IDishServicePort dishServicePort;


    @Test
    void mustCreateAnOrder() {
        OrderRequestDto orderRequestDto = FactoryOrderDataTest.getOrderRequestDto();
        RestaurantModel restaurantModel = FactoryOrderDataTest.getRestaurantModel();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        UserModel userModel = FactoryOrderDataTest.getUserModel();
        OrderModel orderModel = FactoryOrderDataTest.getOrderModel();
        OrderResponseDto orderResponseDto = FactoryOrderDataTest.getOrderResponseDto();
        OrderDishResponseDto orderDishResponseDto = FactoryOrderDataTest.getOrderDishResponseDto();
        DishModel dishModel = FactoryDishDataTest.getDishModle();

        Mockito.when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);
        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            Mockito.when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(orderServicePort.getAllOrdersByUserIdOrderStateIn(any(), any())).thenReturn(true);
            when(dishServicePort.getDish(anyLong())).thenReturn(dishModel);
            when(userRequestMapper.toUser(any())).thenReturn(userModel);
            when(orderServicePort.createOrder(any())).thenReturn(orderModel);
            when(orderResponseMapper.toResponse(any(), any())).thenReturn(orderResponseDto);
            when(orderDishHandler.createOrderDish(any(), anyLong())).thenReturn(orderDishResponseDto);

            Assertions.assertEquals(orderResponseDto, orderHandler.createOrder(orderRequestDto));

            verify(orderServicePort).createOrder(any(OrderModel.class));
        }
    }

    @Test
    void throwDishNotFoundInRestaurantException() {
        OrderRequestDto orderRequestDto = FactoryOrderDataTest.getOrderRequestDto();
        RestaurantModel restaurantModel = FactoryOrderDataTest.getRestaurantModel();
        restaurantModel.setId(2L);
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        UserModel userModel = FactoryOrderDataTest.getUserModel();
        OrderModel orderModel = FactoryOrderDataTest.getOrderModel();
        OrderResponseDto orderResponseDto = FactoryOrderDataTest.getOrderResponseDto();
        DishModel dishModel = FactoryDishDataTest.getDishModle();

        Mockito.when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);
        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            Mockito.when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(orderServicePort.getAllOrdersByUserIdOrderStateIn(any(), any())).thenReturn(true);
            when(userRequestMapper.toUser(any())).thenReturn(userModel);
            when(orderServicePort.createOrder(any())).thenReturn(orderModel);
            when(orderResponseMapper.toResponse(any(), any())).thenReturn(orderResponseDto);
            when(dishServicePort.getDish(anyLong())).thenReturn(dishModel);

            Assertions.assertThrows(
                    DishNotFoundInRestaurantException.class,
                    () -> orderHandler.createOrder(orderRequestDto)
            );
        }
    }

    @Test
    void mustAsingAnOrder() {
        Long orderId = 1L;
        OrderModel orderModel = FactoryOrderDataTest.getOrderModel();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        UserModel userModel = FactoryOrderDataTest.getUserModel();
        OrderDishModel orderDishModel = FactoryOrderDataTest.getOrderDishModel();
        OrderDishResponseDto orderDishResponseDto = FactoryOrderDataTest.orderDishResponseDto();
        OrderResponseDto orderResponseDto = FactoryOrderDataTest.getOrderResponseDto();
        orderResponseDto.setOrderState(OrderState.EN_PREPARACION);

        when(orderServicePort.getOrder(anyLong())).thenReturn(orderModel);

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(userRequestMapper.toUser(any())).thenReturn(userModel);
            when(orderServicePort.createOrder(any())).thenReturn(orderModel);
            when(orderDishServicePort.getAllOrderDishByOrder(anyLong())).thenReturn(List.of(orderDishModel));
            when(orderDishResponseMapper.toResponse(any())).thenReturn(orderDishResponseDto);
            when(orderResponseMapper.toResponse(any(), any())).thenReturn(orderResponseDto);

            Assertions.assertEquals(orderResponseDto, orderHandler.asignAnOrder(orderId));

            verify(orderServicePort).createOrder(any(OrderModel.class));
        }
    }

    @Test
    void mustListAllOrdersByState() {
        OrderState orderState = OrderState.PENDIENTE;
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        RestaurantEmployeeModel restaurantEmployeeModel = FactoryRestaurantDataTest.getRestaurantEmployeeModel();
        RestaurantModel restaurantModel = FactoryOrderDataTest.getRestaurantModel();
        OrderDishModel orderDishModel = FactoryOrderDataTest.getOrderDishModel();
        OrderModel orderModel = FactoryOrderDataTest.getOrderModel();
        OrderStateResponseDto orderStateResponseDto = FactoryOrderDataTest.getOrderStateResponseDto();
        List<OrderStateResponseDto> orderStateResponseDtoList = new ArrayList<>();
        orderStateResponseDtoList.add(orderStateResponseDto);

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(restaurantEmployeeServicePort.getRestaurantByEmployeeId(anyLong())).thenReturn(restaurantEmployeeModel);
            when(restaurantServicePort.getAllRestaurants()).thenReturn(List.of(restaurantModel));
            when(orderDishServicePort.getAllOrderDish()).thenReturn(List.of(orderDishModel));
            when(orderServicePort.getAllOrdersByOrderState(orderState, 1L)).thenReturn(List.of(orderModel));
            when(orderResponseMapper.toReponseList(any(), any(), any())).thenReturn(List.of(orderStateResponseDto));

            Assertions.assertEquals(orderStateResponseDtoList, orderHandler.getAllOrdersByOrderState(orderState));
        }
    }
}