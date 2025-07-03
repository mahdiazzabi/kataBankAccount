package org.example.service.org.example.service.impl;

import org.example.service.IBankAccountPrinterService;
import org.example.service.IBankAccountService;

import java.time.format.DateTimeFormatter;

public class ConsoleAccountPrinterImpl implements IBankAccountPrinterService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void printTransactionHistory(IBankAccountService account) {
        System.out.println("Transaction history: ");
        account.transactions().forEach((uuid, transaction) -> {
            System.out.println("Transaction ID: " + uuid +
                    ", Date: " + formatter.format(transaction.date()) +
                    ", Amount: " + transaction.amount() + " €");
        });
    }

    @Override
    public void printCurrentBalance(IBankAccountService account) {
        System.out.println("Current balance: " + account.currentBalance() + " €");
    }
}
