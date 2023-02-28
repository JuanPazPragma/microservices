package com.example.plaza_comidas.infrastructure.out.jpa.mapper;

import com.example.plaza_comidas.domain.model.OrderDishModel;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {

    @Mapping(source = "orderDishModel.orderId.id", target = "orderId.id")
    @Mapping(source = "orderDishModel.orderId.clientId.id", target = "orderId.clientId")
    @Mapping(source = "orderDishModel.orderId.chefId.id", target = "orderId.chefId")
    OrderDishEntity toOrderDishEntity(OrderDishModel orderDishModel);

    @Mapping(source = "orderDishEntity.orderId.id", target = "orderId.id")
    @Mapping(source = "orderDishEntity.orderId.clientId", target = "orderId.clientId.id")
    @Mapping(source = "orderDishEntity.orderId.chefId", target = "orderId.chefId.id")
    OrderDishModel toOrderDishModel(OrderDishEntity orderDishEntity);

    List<OrderDishModel> toOrderDishModelList(List<OrderDishEntity> orderDishEntityList);

}

