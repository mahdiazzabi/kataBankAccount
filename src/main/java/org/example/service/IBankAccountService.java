package org.example.service;

import org.example.domain.Transaction;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface IBankAccountService {
    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit; must be positive and non-null
     * @throws IllegalArgumentException if the amount is zero or negative
     */
    void deposit(BigDecimal amount);

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw; must be positive and less than or equal to current balance
     * @throws IllegalArgumentException if amount is zero, negative, or exceeds current balance
     */
    void withdraw(BigDecimal amount);

    /**
     * Returns the map of all transactions made on this account.
     *
     * @return a map of transaction IDs to {@link Transaction} objects
     */
    Map<UUID, Transaction> transactions();

    /**
     * Computes the current balance of the account.
     * <p>
     * The balance is calculated as the sum of all deposited transaction amounts.
     *
     * @return the current balance as a {@link BigDecimal}
     */
    default BigDecimal currentBalance() {
        return transactions().values().parallelStream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
