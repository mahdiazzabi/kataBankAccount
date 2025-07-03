package org.example.app;

import org.example.service.IBankAccountService;
import org.example.service.org.example.service.impl.BankClientAccountImpl;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BankAccountClientApp {


    public static void main(String[] args) throws InterruptedException {
        // Create client account in memory for example
        IBankAccountService account = new BankClientAccountImpl();

        // Deposit
        account.deposit(new BigDecimal("100.00"));
        account.deposit(new BigDecimal("250.50"));
        account.deposit(new BigDecimal("50.00"));

        // Display transactions
        System.out.println("Transaction history: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        account.transactions().forEach((date, amount) -> {
            System.out.println(formatter.format(date) + " : " + amount + " €");
        });

        System.out.println("Current balance: " + account.currentBalance() + " €");
    }
}