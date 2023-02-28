package com.example.plaza_comidas.infrastructure.out.jpa.mapper;

import com.example.plaza_comidas.domain.model.OrderModel;
import com.example.plaza_comidas.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {

    @Mapping(source = "orderModel.clientId.id", target = "clientId")
    @Mapping(source = "orderModel.chefId.id", target = "chefId")
    OrderEntity toOrderEntity(OrderModel orderModel);

    @Mapping(source = "clientId", target = "clientId.id")
    @Mapping(source = "chefId", target = "chefId.id")
    OrderModel toOrderModel(OrderEntity orderEntity);

    List<OrderModel> toOrderModelList(List<OrderEntity> orderEntityList);

}
