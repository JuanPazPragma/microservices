package com.example.plaza_comidas.infrastructure.configuration;


import com.example.plaza_comidas.domain.api.ICategoryServicePort;
import com.example.plaza_comidas.domain.api.IDishServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.spi.ICategoryPersistencePort;
import com.example.plaza_comidas.domain.spi.IDishPersistencePort;
import com.example.plaza_comidas.domain.spi.IRestaurantPersistencePort;
import com.example.plaza_comidas.domain.usecase.CategoryUseCase;
import com.example.plaza_comidas.domain.usecase.DishUseCase;
import com.example.plaza_comidas.domain.usecase.RestaurantUseCase;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.ICategoryRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IDishRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IUserRepository;
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

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final IUserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort());
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
