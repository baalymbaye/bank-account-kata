package fr.sg.bankaccountkata.usecases.account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public interface AccountService {
    /**
     * Dépose un montant sur le compte spécifié.
     *
     * @param accountId L'identifiant unique du compte.
     * @param amount Le montant à déposer.
     * @throws AccountNotFoundException Si le compte spécifié n'existe pas.
     */
    void deposit(String accountId, BigDecimal amount) throws AccountNotFoundException;

    /**
     * Retire un montant du compte spécifié.
     *
     * @param accountId L'identifiant unique du compte.
     * @param amount Le montant à retirer.
     * @throws AccountNotFoundException Si le compte spécifié n'existe pas.
     */
    void withdraw(String accountId, BigDecimal amount) throws AccountNotFoundException;
}
