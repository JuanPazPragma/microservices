package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishUpdateRequestDto {
    @NotNull(message = "El id del plato es obligatorio")
    private Long id;
    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private Integer price;
    @NotNull(message = "la descripcion no puede ser nula")
    private String description;
    @NotNull(message = "el campo activo no puede estar en blanco")
    private boolean active;
}
