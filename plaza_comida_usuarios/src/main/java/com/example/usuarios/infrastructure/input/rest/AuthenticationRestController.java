package com.example.usuarios.infrastructure.input.rest;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.dto.response.ResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final IUserHandler userHandler;

    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @RolesAllowed({"ROLE_ADMINISTRADOR"})
    @PostMapping("/owner")
    public ResponseEntity<ResponseDto> ownerRegister(@RequestBody RegisterRequestDto registerRequestDto,
                                                     @RequestHeader(name = "Authorization") String token) {
        ResponseDto responseDto = new ResponseDto();
        UserResponseDto userResponseDto = userHandler.ownerRegister(registerRequestDto, extractToken(token));
        return ResponseEntity.ok(responseDto);
    }

    @RolesAllowed({"ROLE_PROPIETARIO"})
    @PostMapping("/employee")
    public ResponseEntity<ResponseDto> employeeRegister(@RequestBody RegisterRequestDto registerRequestDto,
                                                        @RequestHeader(name = "Authorization") String token) {
        ResponseDto responseDto = new ResponseDto();
        UserResponseDto userResponseDto = userHandler.employeeRegister(registerRequestDto, extractToken(token));
        return ResponseEntity.ok(responseDto);
    }


    private String extractToken(String bearerToken) {
        int index = bearerToken.indexOf(" ");
        return bearerToken.substring(index + 1);
    }
}
