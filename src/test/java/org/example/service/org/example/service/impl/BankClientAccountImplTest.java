package org.example.service.org.example.service.impl;

import org.example.service.IBankAccountService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankClientAccountImplTest {

    private IBankAccountService bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankClientAccountImpl();
    }

    @ParameterizedTest(name = "deposit({0}) should throw IllegalArgumentException")
    @CsvSource({
            "0",
            "-10"
    })
    void deposit_should_throw_when_amount_is_not_positive(BigDecimal invalidAmount) {
        IBankAccountService bankAccount = new BankClientAccountImpl();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(invalidAmount));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 10",
            "0.99, 0.99"
    })
    void deposit_should_add_positive_amount(BigDecimal depositAmount, BigDecimal expectedBalance) {
        IBankAccountService bankAccount = new BankClientAccountImpl();

        bankAccount.deposit(depositAmount);

        Assertions.assertEquals(expectedBalance, bankAccount.currentBalance());
    }

    @Test
    public void test_singleton() {
        // Check both call to getInstance() return the same instance
        BankClientAccountImpl instance1 = BankClientAccountImpl.getInstance();
        BankClientAccountImpl instance2 = BankClientAccountImpl.getInstance();

        assertSame("The instances should be the same (singleton pattern)", instance1, instance2);
    }

    @Test
    public void test_concurrent_deposits() throws InterruptedException {
        bankAccount = new BankClientAccountImpl();
        // Test safety threads with sync deposits
        int numThreads = 10;
        BigDecimal depositAmount = BigDecimal.valueOf(10);
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> bankAccount.deposit(depositAmount));
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for threads to terminate
        for (Thread thread : threads) {
            thread.join();
        }

        // Vérifie que le solde total est le bon après tous les dépôts
        BigDecimal expectedBalance = depositAmount.multiply(BigDecimal.valueOf(numThreads));
        assertEquals(expectedBalance, bankAccount.currentBalance());
    }


}