package com.prestamos_bancarios.prestamos_bancarios.domain.ports.out;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.User;

public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
