package com.prestamos_bancarios.prestamos_bancarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrestamosBancariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestamosBancariosApplication.class, args);
	}

}
