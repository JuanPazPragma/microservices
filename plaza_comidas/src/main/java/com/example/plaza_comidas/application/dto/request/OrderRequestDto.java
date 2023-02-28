package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class OrderRequestDto {
    @NotNull(message = "El campo restaurante es obligatorio")
    private Long restaurantId;
    @NotNull(message = "Las ordenes no pueden estar vacias")
    private List<OrderDishRequestDto> orders;
}
