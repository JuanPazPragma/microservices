package com.example.usuarios.infrastructure.input.rest;

import com.example.usuarios.application.dto.request.AuthenticationRequestDto;
import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final IUserHandler userHandler;

    @GetMapping("/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @PostMapping("/owner")
    public ResponseEntity<AuthenticationResponseDto> ownerRegister(@RequestBody RegisterRequestDto registerRequestDto,
                                                                   @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userHandler.ownerRegister(registerRequestDto, extractToken(token)));
    }

    @PostMapping("/employee")
    public ResponseEntity<AuthenticationResponseDto> employeeRegister(@RequestBody RegisterRequestDto registerRequestDto,
                                                                      @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userHandler.employeeRegister(registerRequestDto, extractToken(token)));
    }


    private String extractToken(String bearerToken) {
        int index = bearerToken.indexOf(" ");
        return bearerToken.substring(index + 1);
    }
}
