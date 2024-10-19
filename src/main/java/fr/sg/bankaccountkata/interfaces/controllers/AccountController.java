package fr.sg.bankaccountkata.interfaces.controllers;

import fr.sg.bankaccountkata.domain.Transaction;
import fr.sg.bankaccountkata.usecases.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable String accountId, @RequestParam BigDecimal amount) throws AccountNotFoundException {
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable String accountId, @RequestParam BigDecimal amount) throws AccountNotFoundException {
        accountService.withdraw(accountId, amount);
    }

    @GetMapping("/{accountId}/statement")
    public ResponseEntity<String> printStatement(@PathVariable String accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.printStatement(accountId));
    }

    @GetMapping("/{accountId}/history")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable String accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.getHistory(accountId));
    }
}
