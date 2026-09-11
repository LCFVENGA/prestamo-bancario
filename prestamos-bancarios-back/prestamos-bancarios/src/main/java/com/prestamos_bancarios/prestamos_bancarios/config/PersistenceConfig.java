package com.prestamos_bancarios.prestamos_bancarios.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence")
@ComponentScan(basePackages = "com.prestamos_bancarios.prestamos_bancarios")
public class PersistenceConfig {
}
