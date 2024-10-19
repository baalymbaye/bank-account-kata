package fr.sg.bankaccountkata.interfaces.controllers;

import fr.sg.bankaccountkata.domain.Transaction;
import fr.sg.bankaccountkata.domain.constants.TransactionType;
import fr.sg.bankaccountkata.usecases.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testDeposit() throws Exception {
        String accountId = "123";
        BigDecimal amount = new BigDecimal("100.00");

        doNothing().when(accountService).deposit(accountId, amount);

        mockMvc.perform(post("/api/accounts/{accountId}/deposit", accountId)
                .param("amount", amount.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(accountService).deposit(accountId, amount);
    }

    @Test
    public void testDepositWithInvalidAccount() throws Exception {
        String accountId = "invalid";
        BigDecimal amount = new BigDecimal("100.00");

        doThrow(new javax.security.auth.login.AccountNotFoundException("Account not found"))
            .when(accountService).deposit(accountId, amount);

        mockMvc.perform(post("/api/accounts/{accountId}/deposit", accountId)
                .param("amount", amount.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(accountService).deposit(accountId, amount);
    }

    @Test
    public void testWithdraw() throws Exception {
        String accountId = "123";
        BigDecimal amount = new BigDecimal("50.00");

        doNothing().when(accountService).withdraw(accountId, amount);

        mockMvc.perform(post("/api/accounts/{accountId}/withdraw", accountId)
                .param("amount", amount.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(accountService).withdraw(accountId, amount);
    }

    @Test
    public void testGetHistory() throws Exception {
        String accountId = "123";
        List<Transaction> transactions = Arrays.asList(
            new Transaction(LocalDateTime.now(), new BigDecimal("10.00"), new BigDecimal("1490.00"), TransactionType.WITHDRAWAL),
            new Transaction(LocalDateTime.now(), new BigDecimal("1.99"), new BigDecimal("1491.99"), TransactionType.DEPOSIT)
        );

        when(accountService.getHistory(accountId)).thenReturn(transactions);

        mockMvc.perform(get("/api/accounts/{accountId}/history", accountId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2));

        verify(accountService).getHistory(accountId);
    }

    @Test
    public void testPrintStatement() throws Exception {
        String accountId = "123";
        String statement = "Account statement for account 123";

        when(accountService.printStatement(accountId)).thenReturn(statement);

        mockMvc.perform(get("/api/accounts/{accountId}/statement", accountId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(statement));

        verify(accountService).printStatement(accountId);
    }
}
