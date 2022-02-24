package com.zanghetsu.britansfer.accounting.service;

import com.zanghetsu.britansfer.accounting.entity.Transfer;
import com.zanghetsu.britansfer.accounting.entity.TransferType;
import com.zanghetsu.britansfer.accounting.repository.AccountingRepository;
import com.zanghetsu.britansfer.accountmanager.entity.Account;
import com.zanghetsu.britansfer.accountmanager.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransferService {

    private AccountingRepository repository;
    private AccountService accountService;

    private boolean checkAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }


    @Transactional
    public boolean makeDeposit(String accountNumber, BigDecimal amount) {
        if (checkAmount(amount)) {
            Account account = accountService.getAccountByAccountNumber(accountNumber);
            accountService.updateBalance(account, accountService.getBalance(account).add(amount));
            repository.save(new Transfer(LocalDateTime.now(), TransferType.DEPOSIT, "External Account", accountNumber));
            return true;
        }
        return false;
    }

    @Transactional
    public boolean makeWithdraw(String accountNumber, BigDecimal amount) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if(checkAmount(amount)){
            if (accountService.checkBalance(account)) {
                if (accountService.getBalance(account).subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
                    accountService.updateBalance(account, accountService.getBalance(account).subtract(amount));
                    repository.save(new Transfer(LocalDateTime.now(), TransferType.WITHDRAW,
                            accountNumber, "External account"));
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    public boolean makeTransfer(String sourceAccount, String targetAccount, BigDecimal amount) { //--> can be account number instead
        Account transfAccount = accountService.getAccountByAccountNumber(sourceAccount);
        Account rescvAccount = accountService.getAccountByAccountNumber(targetAccount);

        if(checkAmount(amount)){
            if (accountService.checkBalance(transfAccount)) {
                if (accountService.getBalance(transfAccount).subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
                    accountService.updateBalance(transfAccount, accountService.getBalance(transfAccount).subtract(amount));
                    accountService.updateBalance(rescvAccount, accountService.getBalance(rescvAccount).add(amount));
                    repository.save(new Transfer(LocalDateTime.now(), TransferType.WIRED_TRANSFER,
                            transfAccount.getAccountNumber(), rescvAccount.getAccountNumber()));
                    return true;
                }
            }
        }
        return false;
    }
}
