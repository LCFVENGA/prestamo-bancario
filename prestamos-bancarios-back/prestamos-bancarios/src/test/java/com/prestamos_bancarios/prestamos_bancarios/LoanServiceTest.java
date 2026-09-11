package com.prestamos_bancarios.prestamos_bancarios;

import com.prestamos_bancarios.prestamos_bancarios.application.services.LoanServiceI;
import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;
import com.prestamos_bancarios.prestamos_bancarios.domain.models.LoanStatus;
import com.prestamos_bancarios.prestamos_bancarios.domain.ports.out.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {
    private LoanRepository repository;
    private LoanServiceI service;

    @BeforeEach
    void setup() {
        repository = mock(LoanRepository.class);
        service = new LoanServiceI(repository);
    }

    @Test
    void testRequestLoan() {
        Loan loan = new Loan();
        loan.setUserId(1L);
        loan.setAmount(BigDecimal.valueOf(1000));

        when(repository.save(any(Loan.class))).thenReturn(loan);

        Loan result = service.requestLoan(loan);
        assertEquals(LoanStatus.PENDING, result.getStatus());
    }
}
