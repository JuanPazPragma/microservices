package com.example.plaza_comidas.application.dto.response;

import com.example.plaza_comidas.domain.model.OrderState;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private Date date;
    private OrderState orderState;
    private RestaurantResponseDto restaurantId;
    private List<OrderDishResponseDto> orders;
}
