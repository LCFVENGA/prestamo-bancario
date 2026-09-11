package com.prestamos_bancarios.prestamos_bancarios.domain.ports.out;


import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;

import java.util.List;

public interface LoanRepository {
    Loan save(Loan loan);
    Loan findById(Long id);
    List<Loan> findAll();
    List<Loan> findAllByUserId(Long userId);
}
