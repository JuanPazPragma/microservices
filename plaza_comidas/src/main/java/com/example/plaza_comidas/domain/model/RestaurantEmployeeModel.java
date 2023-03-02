package com.example.plaza_comidas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEmployeeModel {
    private Long id;
    private RestaurantModel restaurantId;
    private UserModel employeeId;
    private String field;
}
