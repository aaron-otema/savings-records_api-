package com.example.saving_api.controller;

import com.example.saving_api.model.Account;
import com.example.saving_api.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping
    public String addAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return "Account added";

    }
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);

    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id , @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        Account account = accountService.deposit(id, amount);
        return ResponseEntity.ok(account);

    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id , @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        Account account = accountService.withdraw(id, amount);
        return ResponseEntity.ok(account);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Account Deleted");
    }


}
