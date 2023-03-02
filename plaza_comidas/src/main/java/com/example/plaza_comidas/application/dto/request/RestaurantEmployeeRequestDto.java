package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {
    private Long restaurantId;
    private Long employeeId;
    private String field;
    private Long ownerId;
}
