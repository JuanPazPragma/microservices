package com.example.plaza_comidas.infrastructure.input.rest.Client;

import com.example.plaza_comidas.application.dto.response.ResponseClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service", path = "/api/v1/user", url = "http://localhost:8091")
public interface IUserClient {
    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDto> getUserById(@PathVariable Long id);

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseClientDto> getUserByEmail(@PathVariable String email);
}
