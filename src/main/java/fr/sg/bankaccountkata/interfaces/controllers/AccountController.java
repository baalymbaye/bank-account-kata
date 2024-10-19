package fr.sg.bankaccountkata.interfaces.controllers;

import fr.sg.bankaccountkata.usecases.account.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

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
}
