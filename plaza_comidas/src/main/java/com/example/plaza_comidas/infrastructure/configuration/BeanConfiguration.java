package com.example.plaza_comidas.infrastructure.configuration;

import com.example.plaza_comidas.domain.api.ICategoryServicePort;
import com.example.plaza_comidas.domain.api.IDishServicePort;
import com.example.plaza_comidas.domain.api.IOrderDishServicePort;
import com.example.plaza_comidas.domain.api.IOrderServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantEmployeeServicePort;
import com.example.plaza_comidas.domain.api.IRestaurantServicePort;
import com.example.plaza_comidas.domain.spi.ICategoryPersistencePort;
import com.example.plaza_comidas.domain.spi.IDishPersistencePort;
import com.example.plaza_comidas.domain.spi.IOrderDishPersistencePort;
import com.example.plaza_comidas.domain.spi.IOrderPersistencePort;
import com.example.plaza_comidas.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.plaza_comidas.domain.spi.IRestaurantPersistencePort;
import com.example.plaza_comidas.domain.usecase.CategoryUseCase;
import com.example.plaza_comidas.domain.usecase.DishUseCase;
import com.example.plaza_comidas.domain.usecase.OrderDishUseCase;
import com.example.plaza_comidas.domain.usecase.OrderUserCase;
import com.example.plaza_comidas.domain.usecase.RestaurantEmployeeUseCase;
import com.example.plaza_comidas.domain.usecase.RestaurantUseCase;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.OrderDishJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.OrderJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.RestaurantEmployeeJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IRestaurantEmployeeEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.ICategoryRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IDishRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IOrderRepository;
import com.example.plaza_comidas.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
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
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

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
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUserCase(orderPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort() {
        return new OrderDishJpaAdapter(orderDishRepository, orderDishEntityMapper);
    }

    @Bean
    public IOrderDishServicePort orderDishServicePort() {
        return new OrderDishUseCase(orderDishPersistencePort());
    }

    @Bean
    public IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort() {
        return new RestaurantEmployeeJpaAdapter(restaurantEmployeeRepository, restaurantEmployeeEntityMapper);
    }

    @Bean
    public IRestaurantEmployeeServicePort restaurantEmployeeServicePort() {
        return new RestaurantEmployeeUseCase(restaurantEmployeePersistencePort());
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
