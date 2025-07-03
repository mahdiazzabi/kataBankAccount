package org.example.app;
import org.example.service.IBankAccountPrinterService;
import org.example.service.IBankAccountService;
import org.example.service.org.example.service.impl.BankClientAccountImpl;
import org.example.service.org.example.service.impl.ConsoleAccountPrinterImpl;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class BankAccountClientApp {

    private IBankAccountService bankAccount;


    public static void main(String[] args) throws InterruptedException {
        // Create client account in memory for example
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