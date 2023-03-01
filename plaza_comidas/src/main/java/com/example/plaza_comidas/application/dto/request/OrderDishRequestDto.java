package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishRequestDto {
    private Long dishId;
    private Integer amount;
}
