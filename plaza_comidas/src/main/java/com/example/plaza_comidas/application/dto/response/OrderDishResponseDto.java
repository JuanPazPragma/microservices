package com.example.plaza_comidas.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishResponseDto {
    private Long dishId;
    private Integer amount;
}
