package com.example.usuarios.application.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtResponseDto {

    private String token;
    private String bearer;
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;

}
