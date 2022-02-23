package com.zanghetsu.britansfer.accountmanager.service;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import com.zanghetsu.britansfer.accountmanager.entity.CurrencyType;
import com.zanghetsu.britansfer.accountmanager.repository.AccountManagerRepo;
import com.zanghetsu.britansfer.appuser.entity.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountManagerRepo repository;

    private String generateNewAccountNumber() {
        return UUID.randomUUID().toString();
    }

    // maybe removable in the long run
    private boolean checkIfAccountExists(Account account) {
        return repository.findAccountByAccountNumber(account.getAccountNumber()).isPresent();
    }

    private boolean checkIfAccountNumberTaken(String accountNumber) {
        return repository.findAccountByAccountNumber(accountNumber).isPresent();
    }


    public void generateAccount(AppUser appUser) {
        String accountNumber = generateNewAccountNumber();
        if (!checkIfAccountNumberTaken(accountNumber)) {
            accountNumber = generateNewAccountNumber();
        }
        Account account = new Account(accountNumber, BigDecimal.valueOf(0.0), LocalDateTime.now(), CurrencyType.HUF, appUser);
        repository.save(account);
    }


    public BigDecimal getBalance(Account account) {
        if (checkIfAccountExists(account)) {
            return account.getBalance();
        }
        throw new IllegalStateException("There is no account with this number!");
    }

    /*
    public Set<Account> getAccountsForUser(AppUser appUser) {
        Set<Account> accounts = new HashSet<>();
        if (repository.findAccountByUserID(appUser.getId()).isPresent()) {
            accounts.addAll(repository.getAllAccount(appUser.getId()));
        }
        return accounts;
    }*/

    public boolean checkBalance(Account account) {
        return getBalance(account).compareTo(BigDecimal.ZERO) > 0;
    } // --> currency types??

    public void updateBalance(Account account, BigDecimal amount) {
        repository.updateBalance(account.getAccountNumber(), amount);
    }  //---> deposit types to be enumerated??


    public Account getAccountByAccountNumber(String accountNumber) {
        if (repository.findAccountByAccountNumber(accountNumber).isPresent()) {
                return repository.getAccountByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not found!"));
        }
        throw new IllegalStateException("Account Not Found!");
    }
}
