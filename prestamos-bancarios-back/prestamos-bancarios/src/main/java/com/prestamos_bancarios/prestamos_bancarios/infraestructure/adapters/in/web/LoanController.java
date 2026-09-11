package com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.in.web;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;
import com.prestamos_bancarios.prestamos_bancarios.domain.ports.in.LoanService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class LoanController {
    private final LoanService loanService;
    private final UserRepository userRepository;

    public LoanController(LoanService loanService, UserRepository userRepository) {
        this.loanService = loanService;
        this.userRepository = userRepository;
    }

    @PostMapping("/prestamo")
    public Loan requestLoan(@Valid @RequestBody Loan loan, Authentication authentication) {
        loan.setUserId(userRepository.findByUsername(authentication.getName()).getId());
        return loanService.requestLoan(loan);
    }

    @GetMapping
    public List<Loan> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/usuario/{userId}")
    public List<Loan> findAllByUser(@PathVariable Long userId, Authentication authentication) {
        Long authenticatedUserId = userRepository.findByUsername(authentication.getName()).getId();
        if (!authenticatedUserId.equals(userId) && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new org.springframework.security.access.AccessDeniedException("No puede consultar prestamos de otro usuario");
        }
        return loanService.findAllByUserId(userId);
    }

    @GetMapping("/me")
    public List<Loan> findMine(Authentication authentication) {
        Long authenticatedUserId = userRepository.findByUsername(authentication.getName()).getId();
        return loanService.findAllByUserId(authenticatedUserId);
    }

    @GetMapping("/{id}")
    public Loan findById(@PathVariable Long id, Authentication authentication) {
        Loan loan = loanService.findById(id);
        Long authenticatedUserId = userRepository.findByUsername(authentication.getName()).getId();
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!admin && !authenticatedUserId.equals(loan.getUserId())) {
            throw new org.springframework.security.access.AccessDeniedException("No puede consultar este prestamo");
        }
        return loan;
    }

    @PutMapping("/aprobar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Loan approveLoan(@PathVariable Long id) {
        return loanService.approveLoan(id);
    }

    @PutMapping("/rechazar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Loan rejectLoan(@PathVariable Long id) {
        return loanService.rejectLoan(id);
    }
}
