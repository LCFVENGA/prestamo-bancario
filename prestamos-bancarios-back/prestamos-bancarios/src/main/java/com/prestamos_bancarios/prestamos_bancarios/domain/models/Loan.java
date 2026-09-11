package com.prestamos_bancarios.prestamos_bancarios.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private Long id;
    @NotNull(message = "El usuario es obligatorio")
    @Positive(message = "El usuario no es valido")
    private Long userId;
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor que cero")
    private BigDecimal amount;
    @PositiveOrZero(message = "El plazo no puede ser negativo")
    private int termMonths;
    private LoanStatus status;
    private LocalDateTime requestDate;
}
