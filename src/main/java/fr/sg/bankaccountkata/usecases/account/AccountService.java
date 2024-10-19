package fr.sg.bankaccountkata.usecases.account;

import fr.sg.bankaccountkata.domain.Transaction;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

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

    /**
     * Récupère l'historique des transactions pour le compte spécifié.
     *
     * @param accountId L'identifiant unique du compte.
     * @return Une liste d'objets TransactionDto représentant l'historique des transactions.
     * @throws AccountNotFoundException Si le compte spécifié n'existe pas.
     */
    List<Transaction> getHistory(String accountId) throws AccountNotFoundException;

    /**
     * Génère un relevé de compte sous forme de chaîne de caractères.
     *
     * @param accountId L'identifiant unique du compte.
     * @return Une chaîne de caractères représentant le relevé de compte.
     * @throws AccountNotFoundException Si le compte spécifié n'existe pas.
     */
    String printStatement(String accountId) throws AccountNotFoundException;
}
