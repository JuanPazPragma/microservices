package com.example.plaza_comidas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantModel {
    private Long id;
    private String name;
    private String address;
    private Long ownerId;
    private String phoneNumber;
    private String urlLogo;
    private String nit;
}
