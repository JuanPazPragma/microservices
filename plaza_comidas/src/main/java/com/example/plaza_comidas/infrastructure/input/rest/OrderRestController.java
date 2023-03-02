package com.example.plaza_comidas.infrastructure.input.rest;

import com.example.plaza_comidas.application.dto.request.OrderRequestDto;
import com.example.plaza_comidas.application.dto.response.OrderResponseDto;
import com.example.plaza_comidas.application.dto.response.OrderStateResponseDto;
import com.example.plaza_comidas.application.dto.response.ResponseDto;
import com.example.plaza_comidas.application.handler.IOrderHandler;
import com.example.plaza_comidas.domain.model.OrderState;
import com.example.plaza_comidas.infrastructure.exception.DishNotFoundException;
import com.example.plaza_comidas.infrastructure.exception.DishNotFoundInRestaurantException;
import com.example.plaza_comidas.infrastructure.exception.NotEnoughPrivileges;
import com.example.plaza_comidas.infrastructure.exception.RestaurantNotFoundException;
import com.example.plaza_comidas.infrastructure.exception.UserCannotMakeAnOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @RolesAllowed({"ROLE_CLIENTE"})
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderResponseDto orderResponseDto = orderHandler.createOrder(orderRequestDto);

            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderResponseDto);
        } catch (RestaurantNotFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Restaurante no encontrado");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (DishNotFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Plato no encontrado");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (DishNotFoundInRestaurantException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Plato no encontrado en el restaurante");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (UserCannotMakeAnOrderException ex) {
            responseDto.setError(true);
            responseDto.setMessage("El usuario no puede crear otro pedido");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_EMPLEADO"})
    @GetMapping("/get/{orderState}")
    public ResponseEntity<ResponseDto> getOrderByOrderState(@PathVariable OrderState orderState) {
        ResponseDto responseDto = new ResponseDto();

        List<OrderStateResponseDto> orderStateResponseDtoList = orderHandler.getAllOrdersByOrderState(orderState);

        responseDto.setError(false);
        responseDto.setMessage(null);
        responseDto.setData(orderStateResponseDtoList);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_EMPLEADO"})
    @PutMapping("/asignorder/{orderId}")
    public ResponseEntity<ResponseDto> asignOrderToEmployee(@PathVariable Long orderId) {
        ResponseDto responseDto = new ResponseDto();

        OrderResponseDto orderResponseDto = orderHandler.asignAnOrder(orderId);

        responseDto.setError(false);
        responseDto.setMessage(null);
        responseDto.setData(orderResponseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
