package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishRequestDto {
    @NotNull(message = "Nombre es obligatorio")
    private String name;
    @NotNull(message = "Categoria es obligatorio")
    private Long categoryId;
    @NotNull(message = "Descripcion es obligatorio")
    private String description;
    @NotNull(message = "Precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private Integer price;
    @NotNull(message = "Restaurante es obligatorio")
    private Long restaurantId;
    @NotNull(message = "Url de la imagen es obligatorio")
    private String urlImage;
}
