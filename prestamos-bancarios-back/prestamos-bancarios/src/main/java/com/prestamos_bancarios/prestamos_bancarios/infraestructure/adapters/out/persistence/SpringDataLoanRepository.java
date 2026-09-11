package com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence;

import com.prestamos_bancarios.prestamos_bancarios.infraestructure.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataLoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findAllByUserId(Long userId);
}
