package com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;
import com.prestamos_bancarios.prestamos_bancarios.domain.ports.out.LoanRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaLoanRepository implements LoanRepository {
    private final SpringDataLoanRepository repository;

    public JpaLoanRepository(SpringDataLoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        return LoanMapper.toDomain(repository.save(LoanMapper.toEntity(loan)));
    }

    @Override
    public Loan findById(Long id) {
        return repository.findById(id).map(LoanMapper::toDomain).orElseThrow();
    }

    @Override
    public List<Loan> findAll() {
        return repository.findAll().stream().map(LoanMapper::toDomain).toList();
    }

    @Override
    public List<Loan> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream().map(LoanMapper::toDomain).toList();
    }
}
