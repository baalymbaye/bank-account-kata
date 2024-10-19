package fr.sg.bankaccountkata.domain;

import fr.sg.bankaccountkata.domain.constants.TransactionType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Transaction {
    private final LocalDateTime date;
    private final BigDecimal amount;
    private final BigDecimal balance;
    private final TransactionType type;

    public Transaction(LocalDateTime date, BigDecimal amount, BigDecimal balance, TransactionType type) {
        this.date = Objects.requireNonNull(date, "Date cannot be null");
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.balance = Objects.requireNonNull(balance, "Balance cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
    }
}
