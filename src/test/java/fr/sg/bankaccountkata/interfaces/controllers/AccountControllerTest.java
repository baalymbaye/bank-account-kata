package fr.sg.bankaccountkata.interfaces.controllers;

import fr.sg.bankaccountkata.usecases.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
