package fr.sg.bankaccountkata.usecases.account;

import fr.sg.bankaccountkata.domain.Account;
import fr.sg.bankaccountkata.interfaces.gateways.AccountRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

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

    private Account getAccountOrThrow(String accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));
    }
}
