package org.example.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public interface IBankAccountService {

    void deposit(BigDecimal amount);

    Map<LocalDateTime, BigDecimal> transactions();

    default BigDecimal currentBalance() {
        return transactions().values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
