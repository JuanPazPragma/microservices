package com.example.usuarios.infrastructure.input.rest;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.dto.response.JwtResponseDto;
import com.example.usuarios.application.dto.response.ResponseClientDto;
import com.example.usuarios.application.dto.response.ResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.application.handler.IUserHandler;
import com.example.usuarios.infrastructure.exception.EmailAlreadyTaken;
import com.example.usuarios.infrastructure.exception.NoDataFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Email already taken",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) {
        ResponseDto responseDto = new ResponseDto();

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult, responseDto);
        }

        try {
            UserResponseDto userResponseDto = userHandler.register(userRequestDto);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(userResponseDto);
        } catch (EmailAlreadyTaken exception) {
            responseDto.setError(true);
            responseDto.setMessage("El email ingresado ya está en uso");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(userHandler.login(authenticationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDto> getUserById(@PathVariable Long id) {
        ResponseClientDto responseDto = new ResponseClientDto();
        try {
            userHandler.getById(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(userHandler.getById(id));
        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Usuario No encontrado");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno en el servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseClientDto> getUserByEmail(@PathVariable String email) {
        ResponseClientDto responseDto = new ResponseClientDto();
        try {
            userHandler.getByEmail(email);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(userHandler.getByEmail(email));
        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Usuario No encontrado");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno en el servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Register a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Email already taken",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @PostMapping("/client")
    public ResponseEntity<ResponseDto> clientRegister(@Valid @RequestBody RegisterRequestDto registerRequestDto, BindingResult bindingResult) {
        ResponseDto responseDto = new ResponseDto();

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult, responseDto);
        }

        try {
            UserResponseDto userResponseDto = userHandler.clientRegister(registerRequestDto);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(userResponseDto);
        } catch (EmailAlreadyTaken exception) {
            responseDto.setError(true);
            responseDto.setMessage("El email ingresado ya está en uso");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    private ResponseEntity<ResponseDto> ValidationErrors(BindingResult bindingResult, ResponseDto responseDto) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

        responseDto.setError(true);
        responseDto.setMessage("Error en las validaciones");
        responseDto.setData(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
