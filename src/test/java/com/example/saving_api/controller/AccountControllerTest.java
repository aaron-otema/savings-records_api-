package com.example.saving_api.controller;

import com.example.saving_api.model.Account;
import com.example.saving_api.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    Account account1;
    Account account2;
    List<Account> accountList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        account1 = new Account(1L, "Otema", 1000.0);
        account2 = new Account(2L, "Aaron", 2000.0);
        accountList.add(account1);
        accountList.add(account2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addAccountTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(account1);


        when(accountService.createAccount(account1)).thenReturn("Account Created");
        this.mockMvc.perform(post("/api/accounts").contentType(MediaType.APPLICATION_JSON).content(requestJson));

    }

    @Test
    void getAllAccounts() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(accountList);
        this.mockMvc.perform(get("/api/accounts")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAccountByIdTest() throws Exception {
        when(accountService.getAccountById(1L)).thenReturn(account1);
        this.mockMvc.perform(get("/api/accounts/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteAccountTest() throws Exception {
        when(accountService.deleteAccountById(1L)).thenReturn("Account Deleted");
        this.mockMvc.perform(delete("/api/accounts/1")).andDo(print()).andExpect(status().isOk());
    }
}