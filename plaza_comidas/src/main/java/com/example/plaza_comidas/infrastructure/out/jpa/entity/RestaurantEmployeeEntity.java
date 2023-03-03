package com.example.plaza_comidas.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurantemployee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_employee_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurantId;
    private Long employeeId;
    private String field;
}
