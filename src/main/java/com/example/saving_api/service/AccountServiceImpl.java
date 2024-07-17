package com.example.saving_api.service;

import com.example.saving_api.model.Account;
import com.example.saving_api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(Account account) {
        accountRepository.save(account);
        return "Account created";
    }

    @Override
    public Account getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        return account;
    }

    @Override
    public String deleteAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        accountRepository.delete(account);
        return "Account deleted";
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public Account deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        Double total = account.getAccountBalance() + amount;
        account.setAccountBalance(total);
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;

    }

    @Override
    public Account withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        if(account.getAccountBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        Double total = account.getAccountBalance() - amount;
        account.setAccountBalance(total);
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }
}
