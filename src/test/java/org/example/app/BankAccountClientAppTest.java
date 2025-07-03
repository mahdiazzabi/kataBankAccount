package org.example.app;

import org.example.service.IBankAccountService;
import org.example.service.org.example.service.impl.BankClientAccountImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;


public class BankAccountClientAppTest {

    @Test
    public void deposit_negative_should_throw_exception() {
        IBankAccountService account = new BankClientAccountImpl();

        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(new BigDecimal("-50")));
    }

    @Test
    public void deposit_and_balance_should_work_correctly() {
        IBankAccountService account = new BankClientAccountImpl();

        account.deposit(new BigDecimal("100.00"));
        account.deposit(new BigDecimal("250.50"));
        account.deposit(new BigDecimal("50.00"));

        BigDecimal expectedBalance = new BigDecimal("400.50");
        assertEquals(3, account.transactions().size());
        assertEquals(0, account.currentBalance().compareTo(expectedBalance));
    }

    @Test
    public void transaction_history_should_contain_exact_amounts() {
        IBankAccountService account = new BankClientAccountImpl();

        account.deposit(BigDecimal.TEN);
        account.deposit(BigDecimal.TWO);

        Map<?, BigDecimal> tx = account.transactions();
        assertTrue(tx.containsValue(BigDecimal.TEN));
        assertTrue(tx.containsValue(BigDecimal.TWO));
    }
}