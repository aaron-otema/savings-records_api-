package com.example.saving_api.repository;

import com.example.saving_api.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1L, "Otema", 1000.0);
        accountRepository.save(account);
    }
    @AfterEach
    void tearDown() {
        account = null;
        accountRepository.deleteAll();
    }
    //Test success
    @Test
    void testFindByUserName_found(){
        List<Account> accountsList = accountRepository.findByUserName("Otema");
        assertThat(accountsList.get(0).getId()).isEqualTo(account.getId());
        assertThat(accountsList.get(0).getUserName()).isEqualTo(account.getUserName());
    }
    //Test failure
    @Test
    void testFindByUserName_notFound(){
        List<Account> accountsList = accountRepository.findByUserName("Aaron");
        assertThat(accountsList.isEmpty()).isTrue();
    }


}
