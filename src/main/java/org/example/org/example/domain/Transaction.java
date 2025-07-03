package org.example.org.example.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(UUID transactionId, LocalDateTime date, BigDecimal amount) {
    public Transaction(BigDecimal amount) {
        this(UUID.randomUUID(), LocalDateTime.now(), amount);
    }
}