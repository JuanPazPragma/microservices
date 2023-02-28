package com.example.plaza_comidas.infrastructure.input.rest;

import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.dto.response.ResponseDto;
import com.example.plaza_comidas.application.handler.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        ResponseDto responseDto = new ResponseDto();

        OrderResponseDto orderResponseDto = orderHandler.createOrder(orderRequestDto);

        responseDto.setError(false);
        responseDto.setMessage(null);
        responseDto.setData(orderResponseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

}
