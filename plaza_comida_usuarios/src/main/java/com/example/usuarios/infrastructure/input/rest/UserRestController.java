package com.example.usuarios.infrastructure.input.rest;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.dto.response.JwtResponseDto;
import com.example.usuarios.application.dto.response.ResponseClientDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.application.handler.IUserHandler;
import com.example.usuarios.infrastructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userHandler.register(userRequestDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(userHandler.authenticate(authenticationRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(userHandler.login(authenticationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDto> getUserById(@PathVariable Long id) {
        ResponseClientDto responseDto = new ResponseClientDto();
        try {
            UserResponseDto userResponseDto = userHandler.getById(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(userHandler.getById(id));
        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Usuario No encontrado");
            responseDto.setData(null);
        } catch (Exception e) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno en el servidor");
            responseDto.setData(null);
        }
        return ResponseEntity.ok(responseDto);
    }

    private ResponseEntity<HashMap> ValidationErrors(BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

        HashMap<String, Object> message = new HashMap<String, Object>();
        message.put("Error en el formulario", true);
        message.put("Errores", errors);
        return ResponseEntity.badRequest().body(message);
    }
}
