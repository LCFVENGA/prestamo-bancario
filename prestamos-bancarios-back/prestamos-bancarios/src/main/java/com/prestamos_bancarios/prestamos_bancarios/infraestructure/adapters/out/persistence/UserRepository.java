package com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence;

import com.prestamos_bancarios.prestamos_bancarios.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
