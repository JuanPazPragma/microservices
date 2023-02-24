package com.example.usuarios.infrastructure.configuration;

import com.example.usuarios.domain.api.IRolServicePort;
import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.spi.IRolPersistencePort;
import com.example.usuarios.domain.spi.IUserPersistencePort;
import com.example.usuarios.domain.usecase.RolUseCase;
import com.example.usuarios.domain.usecase.UserUseCase;
import com.example.usuarios.infrastructure.out.jpa.adapter.RolJpaAdapter;
import com.example.usuarios.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.example.usuarios.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.example.usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import com.example.usuarios.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolPersistencePort rolPersistencePort() {
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort() {
        return new RolUseCase(rolPersistencePort());
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
