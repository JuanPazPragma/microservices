package com.example.plaza_comidas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishModel {
    private Long id;
    private String name;
    private CategoryModel categoryId;
    private String description;
    private Integer price;
    private RestaurantModel restaurantId;
    private String urlImage;
    private Boolean active;
}
