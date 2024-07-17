package com.example.saving_api.repository;

import com.example.saving_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserName(String userName);

}
