package com.example.plaza_comidas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {
    private Long id;
    private UserModel clientId;
    private Date date;
    private OrderState orderState;
    private UserModel chefId;
    private RestaurantModel restaurantId;
}
