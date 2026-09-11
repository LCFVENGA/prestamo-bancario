package com.prestamos_bancarios.prestamos_bancarios.config;

import com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence.UserRepository;
import com.prestamos_bancarios.prestamos_bancarios.infraestructure.entities.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seedUsers(UserRepository repository, PasswordEncoder encoder) {
        return args -> {
            if (repository.findByUsername("admin") == null) {
                repository.save(UserEntity.builder().username("admin").password(encoder.encode("admin123")).rol("ROLE_ADMIN").build());
            }
            if (repository.findByUsername("user") == null) {
                repository.save(UserEntity.builder().username("user").password(encoder.encode("user123")).rol("ROLE_USER").build());
            }
        };
    }
}
