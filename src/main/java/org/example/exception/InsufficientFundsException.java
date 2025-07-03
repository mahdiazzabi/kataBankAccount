package org.example.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(BigDecimal amount, BigDecimal currentBalance) {
        super("Insufficient funds: attempted to withdraw " + amount + " €, but current balance is " + currentBalance + " €.");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
