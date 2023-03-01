package com.example.plaza_comidas.infrastructure.input.rest;

import com.example.plaza_comidas.application.dto.request.OrderDishRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderDishResponseDto;
import com.example.plaza_comidas.application.dto.response.ResponseDto;
import com.example.plaza_comidas.application.handler.IOrderDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orderdish")
@RequiredArgsConstructor
public class OrderDishRestController {

    private final IOrderDishHandler orderDishHandler;

    @PostMapping("/create/{orderId}")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderDishRequestDto orderDishRequestDto,
                                                   @PathVariable Long orderId) {
        ResponseDto responseDto = new ResponseDto();

        OrderDishResponseDto orderDishResponseDto = orderDishHandler.createOrderDish(orderDishRequestDto, orderId);

        responseDto.setError(false);
        responseDto.setMessage(null);
        responseDto.setData(orderDishResponseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
