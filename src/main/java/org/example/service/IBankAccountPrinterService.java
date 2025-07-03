package org.example.service;

public interface IBankAccountPrinterService {

    void printTransactionHistory(IBankAccountService account);
    void printCurrentBalance(IBankAccountService account);
}
