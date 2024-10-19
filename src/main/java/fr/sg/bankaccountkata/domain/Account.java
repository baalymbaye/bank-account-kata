package fr.sg.bankaccountkata.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class Account {
    private final String id;
    private BigDecimal balance;

    public Account(String id) {
        this.id = Objects.requireNonNull(id, "Account ID cannot be null");
        this.balance = BigDecimal.ZERO;
    }
}
