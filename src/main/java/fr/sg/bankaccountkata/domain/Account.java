package fr.sg.bankaccountkata.domain;

import fr.sg.bankaccountkata.domain.constants.TransactionType;
import fr.sg.bankaccountkata.interfaces.exceptions.InvalidAmountException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Account {
    private final String id;
    private BigDecimal balance;
    private final List<Transaction> transactions;

    public Account(String id) {
        this.id = Objects.requireNonNull(id, "Account ID cannot be null");
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
        addOperation(amount, TransactionType.DEPOSIT);
    }

    private void validateAmount(BigDecimal amount) {
        if (null == amount || 0 >= amount.compareTo(BigDecimal.ZERO)) {
            throw new InvalidAmountException("Amount must be positive");
        }
    }

    private void addOperation(BigDecimal amount, TransactionType type) {
        transactions.add(new Transaction(LocalDateTime.now(), amount, balance, type));
    }
}
