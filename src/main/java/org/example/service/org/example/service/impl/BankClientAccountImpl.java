package org.example.service.org.example.service.impl;

import org.example.org.example.domain.Transaction;
import org.example.service.IBankAccountService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
/**
 * In-memory implementation of the {@link IBankAccountService} interface.
 * <p>
 * Stores transactions in a thread-safe map and supports singleton access.
 * Intended for demo or testing purposes; can be refactored to use persistent storage.
 */
public class BankClientAccountImpl implements IBankAccountService {
    /**
     * Thread-safe storage for account transactions.
     * Keys are unique transaction IDs, values are {@link Transaction} records.
     */
    private final Map<UUID, Transaction>  transactions = new ConcurrentHashMap<>();

    private static final Logger logger = Logger.getLogger(BankClientAccountImpl.class.getName());

    /**
     * Singleton instance of the account.
     */
    private static volatile BankClientAccountImpl instance;

    /**
     * Private constructor to prevent direct instantiation.
     * Use {@link #getInstance()} to retrieve the singleton instance.
     */
    BankClientAccountImpl() {}

    /**
     * Returns the singleton instance of {@code BankClientAccountImpl}.
     * <p>
     * Thread-safe using double-checked locking.
     *
     * @return the singleton instance
     */
    public static BankClientAccountImpl getInstance() {
        if (instance == null) {
            synchronized (BankClientAccountImpl.class) {
                if (instance == null) {
                    instance = new BankClientAccountImpl();
                    logger.info("BankClientAccountImpl instance created.");
                }
            }
        }
        return instance;
    }

    /**
     * Adds a deposit transaction with the given amount.
     * <p>
     * A {@link Transaction} is created and added to the internal map.
     *
     * @param amount the amount to deposit; must be positive
     * @throws IllegalArgumentException if amount is zero or negative
     */
    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.severe("Deposit failed: Amount must be positive. Provided amount: " + amount);
            throw new IllegalArgumentException("Amount must be positive");
        }

        final var transaction = new Transaction(amount);
        transactions.put(transaction.transactionId(), transaction);
        logger.info("Deposit successful. Amount: " + amount + " €, Transaction ID: " + transaction.transactionId());
    }

    /**
     * Returns an unmodifiable view of the transaction history.
     *
     * @return a read-only map of transaction IDs to {@link Transaction} objects
     */
    @Override
    public Map<UUID, Transaction> transactions() {
        return Collections.unmodifiableMap(transactions);
    }
}
