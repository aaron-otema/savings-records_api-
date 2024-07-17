package com.example.saving_api.service;

import com.example.saving_api.model.Account;

import java.util.List;

public interface AccountService {
    String createAccount(Account account);
    Account getAccountById(Long id);
    String deleteAccountById(Long id);
    List<Account> getAllAccounts();
    Account deposit(Long id, double amount);
    Account withdraw(Long id, double amount);

}
