package org.example.app;
import org.example.service.IBankAccountPrinterService;
import org.example.service.IBankAccountService;
import org.example.service.impl.BankClientAccountImpl;
import org.example.service.impl.ConsoleAccountPrinterImpl;

import java.math.BigDecimal;

public class BankAccountClientApp {

    public static void main(String[] args) {
        // Create client account in memory for kata example
        IBankAccountService account = BankClientAccountImpl.getInstance();

        // Deposit
        account.deposit(new BigDecimal("100.00"));
        account.deposit(new BigDecimal("250.50"));
        account.deposit(new BigDecimal("50.00"));

        // Display transactions and Current Balance
        IBankAccountPrinterService printer = new ConsoleAccountPrinterImpl();
        printer.printTransactionHistory(account);
        printer.printCurrentBalance(account);
    }
}