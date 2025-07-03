package org.example.service.org.example.service.impl;

import org.example.service.IBankAccountService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BankClientAccountImpl implements IBankAccountService {
    //TODO Refactor InMemoryList to persistant H2 database records
    private final Map<LocalDateTime, BigDecimal>  transactions = new HashMap<>();

    /**
     * @param amount
     * Should be BigDecimal for immutability
     * TODO : perf and sync for safe multithreading
     */
    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");
        transactions.put(LocalDateTime.now(), amount);
    }

    /**
     * @return current transcations
     */
    @Override
    public Map<LocalDateTime, BigDecimal> transactions() {
        return transactions;
    }


}
