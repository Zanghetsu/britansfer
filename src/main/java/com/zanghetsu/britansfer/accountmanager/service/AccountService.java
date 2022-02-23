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

    public boolean checkBalance(Account account) {
        return getBalance(account).compareTo(BigDecimal.ZERO) > 0;
    }

    public void updateBalance(Account account, BigDecimal amount) {
        repository.updateBalance(account.getAccountNumber(), amount);
    }


    public Account getAccountByAccountNumber(String accountNumber) {
        if (repository.findAccountByAccountNumber(accountNumber).isPresent()) {
                return repository.getAccountByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not found!"));
        }
        throw new IllegalStateException("Account Not Found!");
    }
}
