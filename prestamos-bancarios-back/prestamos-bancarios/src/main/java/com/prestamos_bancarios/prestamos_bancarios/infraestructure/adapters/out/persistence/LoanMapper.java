package com.prestamos_bancarios.prestamos_bancarios.infraestructure.adapters.out.persistence;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.Loan;
import com.prestamos_bancarios.prestamos_bancarios.infraestructure.entities.LoanEntity;

public class LoanMapper {

    public static Loan toDomain(LoanEntity entity) {
        return Loan.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .amount(entity.getAmount())
                .termMonths(entity.getTermMonths())
                .status(entity.getStatus())
                .requestDate(entity.getRequestDate())
                .build();
    }

    public static LoanEntity toEntity(Loan loan) {
        return LoanEntity.builder()
                .id(loan.getId())
                .userId(loan.getUserId())
                .amount(loan.getAmount())
                .termMonths(loan.getTermMonths())
                .status(loan.getStatus())
                .requestDate(loan.getRequestDate())
                .build();
    }
}

