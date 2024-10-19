package fr.sg.bankaccountkata.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("999");
    }

    @Test
    void deposit_shouldIncreaseBalance() {
        account.deposit(new BigDecimal("1000.00"));

        assertEquals(new BigDecimal("1000.00"), account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }
}
