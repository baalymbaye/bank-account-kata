package fr.sg.bankaccountkata.domain.utils;

import fr.sg.bankaccountkata.domain.Transaction;
import fr.sg.bankaccountkata.domain.constants.TransactionType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatementPrinter {
    public String print(List<Transaction> operations) {
        StringBuilder statement = new StringBuilder("Date | Amount | Balance\n");
        operations.forEach(op -> statement.append(formatOperation(op)));
        return statement.toString();
    }

    private String formatOperation(Transaction transaction) {
        return String.format("%s | %s%s | %s\n",
            formatDate(transaction.getDate()),
            transaction.getType() == TransactionType.DEPOSIT ? "+" : "-",
            formatAmount(transaction.getAmount()),
            formatAmount(transaction.getBalance()));
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String formatAmount(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP).toString();
    }
}
