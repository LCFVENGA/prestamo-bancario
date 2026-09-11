package com.prestamos_bancarios.prestamos_bancarios.infraestructure.entities;

import com.prestamos_bancarios.prestamos_bancarios.domain.models.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private BigDecimal amount;

    private int termMonths;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private LocalDateTime requestDate;
}
