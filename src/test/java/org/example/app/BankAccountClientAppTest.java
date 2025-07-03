package org.example.app;

import org.example.exception.InsufficientFundsException;
import org.example.service.IBankAccountService;
import org.example.service.impl.BankClientAccountImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class BankAccountClientAppTest {

    @Test
    public void deposit_negative_should_throw_exception() {
        IBankAccountService account = BankClientAccountImpl.getInstance();
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(new BigDecimal("-50")));
    }

    @Test
    public void deposit_and_balance_should_work_correctly() {
        IBankAccountService account = BankClientAccountImpl.getInstance();

        account.deposit(new BigDecimal("100.00"));
        account.deposit(new BigDecimal("250.50"));
        account.deposit(new BigDecimal("50.00"));
        account.withdraw(new BigDecimal("50.00"));
        account.withdraw(new BigDecimal("50.00"));

        BigDecimal expectedBalance = new BigDecimal("300.50");
        assertEquals(5, account.transactions().size());
        assertEquals(0, account.currentBalance().compareTo(expectedBalance));
        assertTrue(account.transactions().values().stream()
                .anyMatch(transaction  -> transaction.amount().compareTo(new BigDecimal("-50.00")) == 0));

    }


    @Test
    public void withdraw_more_than_balance_should_throw_exception() {
        IBankAccountService account = BankClientAccountImpl.getInstance();

        account.deposit(BigDecimal.ONE);
        assertThrows(InsufficientFundsException.class,
                () -> account.withdraw(BigDecimal.valueOf(Double.MAX_VALUE)));
    }

}