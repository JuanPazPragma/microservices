package com.example.factory;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.response.DishResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.dto.response.RestaurantResponseDto;
import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.domain.model.RestaurantModel;
import com.example.plaza_comidas.domain.model.RolModel;
import com.example.plaza_comidas.domain.model.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactoryOrderDataTest {

    public static OrderRequestDto getOrderRequestDto() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        List<OrderDishRequestDto> orderDishRequestDtoList = new ArrayList<>();
        orderDishRequestDtoList.add(getOrderDishRequestDto());
        orderDishRequestDtoList.add(getOrderDishRequestDto());

        orderRequestDto.setRestaurantId(1L);
        orderRequestDto.setOrders(orderDishRequestDtoList);

        return orderRequestDto;
    }

    public static OrderDishRequestDto getOrderDishRequestDto() {
        OrderDishRequestDto orderDishRequestDto = new OrderDishRequestDto();

        orderDishRequestDto.setDishId(1L);
        orderDishRequestDto.setAmount(3);

        return orderDishRequestDto;
    }

    public static RestaurantModel getRestaurantModel() {
        RestaurantModel expectedRestaurant = new RestaurantModel();

        expectedRestaurant.setId(1L);
        expectedRestaurant.setName("Corral");
        expectedRestaurant.setAddress("Calle 5");
        expectedRestaurant.setOwnerId(4L);
        expectedRestaurant.setPhoneNumber("+10000");
        expectedRestaurant.setUrlLogo("logoUrl");
        expectedRestaurant.setNit("20000");

        return expectedRestaurant;
    }

    public static UserModel getUserModel() {
        UserModel userModel = new UserModel();

        userModel.setId(1L);
        userModel.setName("Juan");
        userModel.setLastName("Paz");
        userModel.setIdNumber("100");
        userModel.setPhone("123");
        userModel.setEmail("juan@gmail.com");
        userModel.setPassword("1234");
        userModel.setRolId(getRolModelClient());

        return userModel;
    }

    public static RolModel getRolModelClient() {
        RolModel rolModel = new RolModel();
        rolModel.setId(4L);
        rolModel.setName("ROLE_CLIENTE");
        rolModel.setDescription("Cliente");
        return rolModel;
    }

    public static OrderModel getOrderModel() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        orderModel.setClientId(getUserModel());
        orderModel.setDate(new Date());
        orderModel.setOrderState(OrderState.PENDIENTE);
        orderModel.setChefId(null);
        orderModel.setRestaurantId(getRestaurantModel());
        return orderModel;
    }

    public static OrderResponseDto getOrderResponseDto() {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        List<OrderDishResponseDto> orderDishResponseDtoList = new ArrayList<>();
        orderDishResponseDtoList.add(getOrderDishResponseDto());
        orderDishResponseDtoList.add(getOrderDishResponseDto());

        orderResponseDto.setOrderId(1L);
        orderResponseDto.setDate(new Date());
        orderResponseDto.setOrderState(OrderState.PENDIENTE);
        orderResponseDto.setRestaurantId(getRestaurantResponseDto());
        orderResponseDto.setOrders(orderDishResponseDtoList);

        return orderResponseDto;
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

    public static OrderDishResponseDto getOrderDishResponseDto() {
        OrderDishResponseDto orderDishResponseDto = new OrderDishResponseDto();


        return orderDishResponseDto;
    }



}
