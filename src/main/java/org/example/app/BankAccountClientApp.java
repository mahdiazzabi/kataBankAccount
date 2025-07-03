package org.example.app;
import org.example.service.IBankAccountService;
import org.example.service.org.example.service.impl.BankClientAccountImpl;

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
        System.out.println("Transaction history: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        account.transactions().forEach((uuid, transaction) -> {
            System.out.println("Transaction ID: " + uuid + ", Date: " + formatter.format(transaction.date())
                    + " , Amount: " + transaction.amount() + " €");
        });
        System.out.println("Current balance: " + account.currentBalance() + " €");
    }
}