package com.example.usuarios.infrastructure.input.rest;

import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping("/")
    public ResponseEntity<HashMap> saveUser(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult);
        }

        userHandler.saveUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private ResponseEntity<HashMap> ValidationErrors(BindingResult bindingResult){
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

        HashMap<String, Object> message = new HashMap<String,Object>();
        message.put("Error en el formulario",true);
        message.put("Errores",errors);
        return ResponseEntity.badRequest().body(message);
    }
}
