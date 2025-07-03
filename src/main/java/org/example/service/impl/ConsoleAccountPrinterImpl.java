package org.example.service.impl;

import org.example.domain.Transaction;
import org.example.service.IBankAccountPrinterService;
import org.example.service.IBankAccountService;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class ConsoleAccountPrinterImpl implements IBankAccountPrinterService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void printTransactionHistory(IBankAccountService account) {
        System.out.println("Transaction history:");

        List<Transaction> sorted = account.transactions().values().stream()
                .sorted(Comparator.comparing(Transaction::date))
                .toList();

        BigDecimal runningBalance = BigDecimal.ZERO;

        for (Transaction transaction : sorted) {
            runningBalance = runningBalance.add(transaction.amount());

            System.out.println("Transaction ID: " + transaction.transactionId() +
                    ", Date: " + formatter.format(transaction.date()) +
                    ", Amount: " + transaction.amount() + " €" +
                    ", Balance: " + runningBalance + " €");
        }
    }

    @Override
    public void printCurrentBalance(IBankAccountService account) {
        System.out.println("Current balance: " + account.currentBalance() + " €");
    }
}
