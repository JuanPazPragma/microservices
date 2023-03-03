package com.example.plaza_comidas.application.handler.impl;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.request.UserRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderStateResponseDto;
import com.example.plaza_comidas.application.handler.IOrderDishHandler;
import com.example.plaza_comidas.application.handler.IOrderHandler;
import com.example.plaza_comidas.application.mapper.request.IOrderRequestMapper;
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
import com.example.plaza_comidas.infrastructure.configuration.FeignClientInterceptorImp;
import com.example.plaza_comidas.infrastructure.exception.DishNotFoundInRestaurantException;
import com.example.plaza_comidas.infrastructure.exception.NoDataFoundException;
import com.example.plaza_comidas.infrastructure.input.rest.Client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
    private final IOrderDishResponseMapper orderDishResponseMapper;
    private final IOrderDishServicePort orderDishServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderDishHandler orderDishHandler;
    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final JwtHandler jwtHandler;
    private final IUserClient userClient;
    private final IUserRequestMapper userRequestMapper;
    private final IDishServicePort dishServicePort;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(orderRequestDto.getRestaurantId());
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();
        List<OrderState> orderStateList = Arrays.asList(OrderState.EN_PREPARACION, OrderState.PENDIENTE, OrderState.LISTO);

        orderServicePort.getAllOrdersByUserIdOrderStateIn(userRequestDto.getId(), orderStateList);

        OrderModel orderModel = new OrderModel();
        orderModel.setClientId(userRequestMapper.toUser(userRequestDto));
        orderModel.setDate(new Date());
        orderModel.setOrderState(OrderState.PENDIENTE);
        orderModel.setChefId(null);
        orderModel.setRestaurantId(restaurantModel);

        OrderModel orderModelResponse = orderServicePort.createOrder(orderModel);

        List<OrderDishRequestDto> orderDishRequestDtoList = orderRequestDto.getOrders();

        List<OrderDishResponseDto> orderDishResponseDtoList =
                orderDishRequestDtoList.stream()
                        .map(orderDish -> {

                            if (!Objects.equals(dishServicePort.getDish(orderDish.getDishId()).getRestaurantId().getId(), restaurantModel.getId())) {
                                throw new DishNotFoundInRestaurantException();
                            }

                            return orderDishHandler.createOrderDish(orderDish, orderModelResponse.getId());
                        }).toList();

        return orderResponseMapper.toResponse(orderModelResponse, orderDishResponseDtoList);
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderStateResponseDto> getAllOrdersByOrderState(OrderState orderState) {
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();

        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeServicePort.getRestaurantByEmployeeId(userRequestDto.getId());

        return orderResponseMapper.toReponseList(orderServicePort.getAllOrdersByOrderState(orderState, restaurantEmployeeModel.getRestaurantId().getId()), restaurantServicePort.getAllRestaurants(), orderDishServicePort.getAllOrderDish());
    }

    @Override
    public OrderResponseDto asignAnOrder(Long orderId) {
        OrderModel orderModel = orderServicePort.getOrder(orderId);

        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();

        orderModel.setChefId(userRequestMapper.toUser(userRequestDto));
        orderModel.setOrderState(OrderState.EN_PREPARACION);

        OrderModel orderModelResponse = orderServicePort.createOrder(orderModel);

        List<OrderDishModel> orderDishModelList = orderDishServicePort.getAllOrderDishByOrder(orderId);

        List<OrderDishResponseDto> orderDishResponseDtoList = orderDishModelList.stream().map(orderDishResponseMapper::toResponse).toList();

        return orderResponseMapper.toResponse(orderModelResponse, orderDishResponseDtoList);
    }

}
