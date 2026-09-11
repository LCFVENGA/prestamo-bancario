package com.prestamos_bancarios.prestamos_bancarios.application.services;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;
import com.prestamos_bancarios.prestamos_bancarios.domain.models.LoanStatus;
import com.prestamos_bancarios.prestamos_bancarios.domain.ports.in.LoanService;
import com.prestamos_bancarios.prestamos_bancarios.domain.ports.out.LoanRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheEvict;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanServiceI implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceI(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan requestLoan(Loan loan) {
        loan.setStatus(LoanStatus.PENDING);
        loan.setRequestDate(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    @Override
    @Transactional
    @CacheEvict(value = "loans", allEntries = true)
    public Loan approveLoan(Long id) {
        Loan loan = loanRepository.findById(id);
        loan.setStatus(LoanStatus.APPROVED);
        return loanRepository.save(loan);
    }

    @Override
    @Transactional
    @CacheEvict(value = "loans", allEntries = true)
    public Loan rejectLoan(Long id) {
        Loan loan = loanRepository.findById(id);
        loan.setStatus(LoanStatus.REJECTED);
        return loanRepository.save(loan);
    }

    @Override
    @Cacheable("loans")
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Loan findById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public List<Loan> findAllByUserId(Long userId) {
        return loanRepository.findAllByUserId(userId);
    }
}
