package com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.in.web;

import com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence.UserRepository;
import com.prestamos_bancarios.prestamos_bancarios.infraestructure.entities.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UserResponse> findAll() { return repository.findAll().stream().map(UserResponse::from).toList(); }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) { return UserResponse.from(repository.findById(id).orElseThrow()); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        UserEntity user = UserEntity.builder().username(request.username()).password(passwordEncoder.encode(request.password())).rol(normalizeRole(request.role())).build();
        return UserResponse.from(repository.save(user));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        UserEntity user = repository.findById(id).orElseThrow();
        user.setUsername(request.username());
        user.setRol(normalizeRole(request.role()));
        if (!request.password().isBlank()) user.setPassword(passwordEncoder.encode(request.password()));
        return UserResponse.from(repository.save(user));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.deleteById(id); }

    private String normalizeRole(String role) { return role.startsWith("ROLE_") ? role : "ROLE_" + role; }

    public record UserRequest(@NotBlank String username, @NotBlank String password, @NotBlank @Pattern(regexp = "USER|ADMIN|ROLE_USER|ROLE_ADMIN") String role) {}
    public record UserResponse(Long id, String username, String role) {
        static UserResponse from(UserEntity user) { return new UserResponse(user.getId(), user.getUsername(), user.getRol()); }
    }
}
