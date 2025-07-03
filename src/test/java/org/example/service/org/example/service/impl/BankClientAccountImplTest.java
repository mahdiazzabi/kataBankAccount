package org.example.service.org.example.service.impl;

import org.example.service.IBankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;

public class BankClientAccountImplTest {

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


}