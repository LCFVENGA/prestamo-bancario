package com.prestamos_bancarios.prestamos_bancarios.domain.ports.in;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;

import java.util.List;

public interface LoanService {
    Loan requestLoan(Loan loan);
    Loan approveLoan(Long id);
    Loan rejectLoan(Long id);
    List<Loan> findAll();
    Loan findById(Long id);
    List<Loan> findAllByUserId(Long userId);
}

