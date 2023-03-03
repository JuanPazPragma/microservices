package com.example.plaza_comidas.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {
    private String name;
    private CategoryResponseDto categoryId;
    private String description;
    private Integer price;
    private RestaurantResponseDto restaurantId;
    private String urlImage;
    private Boolean active;
}
