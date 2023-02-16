package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishUpdateRequestDto {
    @NotNull(message = "El id del plato es obligatorio")
    private Long id;
    private Float price;
    private String description;
}
