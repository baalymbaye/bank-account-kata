package fr.sg.bankaccountkata.usecases.account;

import fr.sg.bankaccountkata.domain.Account;
import fr.sg.bankaccountkata.domain.Transaction;
import fr.sg.bankaccountkata.domain.utils.StatementPrinter;
import fr.sg.bankaccountkata.interfaces.gateways.AccountRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deposit(String accountId, BigDecimal amount) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountId);
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(String accountId, BigDecimal amount) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountId);
        account.withdraw(amount);
        accountRepository.save(account);
    }

    @Override
    public List<Transaction> getHistory(String accountId) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountId);
        return account.getTransactions();
    }

    @Override
    public String printStatement(String accountId) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountId);
        return new StatementPrinter().print(account.getTransactions());
    }

    private Account getAccountOrThrow(String accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));
    }
}
