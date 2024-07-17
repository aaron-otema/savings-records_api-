package com.example.saving_api.service;

import com.example.saving_api.model.Account;
import com.example.saving_api.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;
    AutoCloseable autoCloseable;
    Account account;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(accountRepository);
        account = new Account(1L, "Otema", 1000.0);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateAccount() {
        mock(Account.class);
        mock(AccountRepository.class);

        when(accountRepository.save(account)).thenReturn(account);
        assertThat(accountService.createAccount(account)).isEqualTo("Account created");
    }

    @Test
    void testGetAccountById() {
        mock(Account.class);
        mock(AccountRepository.class);

        when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(account));
        assertThat(accountService.getAccountById(1L)).isEqualTo(account);

    }

    @Test
    void testGetAllAccounts() {
        mock(Account.class);
        mock(AccountRepository.class);

        when(accountRepository.findAll()).thenReturn(new ArrayList<Account>(Collections.singleton(account)));
        assertThat(accountService.getAllAccounts().size()).isEqualTo(1);
    }
    //    @Test
//    void testDeleteAccountById() {
//        mock(Account.class);
//        mock(AccountRepository.class, Mockito.CALLS_REAL_METHODS);
//
//        doAnswer(Answers.CALLS_REAL_METHODS).when(accountRepository).deleteById(any());
//        assertThat(accountService.deleteAccountById(1L)).isEqualTo("Account deleted");
//    }
}