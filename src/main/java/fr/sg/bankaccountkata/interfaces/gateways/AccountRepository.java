package fr.sg.bankaccountkata.interfaces.gateways;

import fr.sg.bankaccountkata.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    public Optional<Account> findById(String accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }
}
