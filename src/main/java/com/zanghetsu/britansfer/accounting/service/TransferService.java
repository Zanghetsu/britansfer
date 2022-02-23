package com.zanghetsu.britansfer.accounting.service;

import com.zanghetsu.britansfer.accounting.entity.Transfer;
import com.zanghetsu.britansfer.accounting.entity.TransferType;
import com.zanghetsu.britansfer.accounting.repository.AccountingRepository;
import com.zanghetsu.britansfer.accountmanager.entity.Account;
import com.zanghetsu.britansfer.accountmanager.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    private AccountingRepository repository;
    private AccountService accountService;

    @Transactional
    public boolean makeDeposit(String accountNumber, BigDecimal amount) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        accountService.updateBalance(account, amount);
        repository.save(new Transfer(LocalDateTime.now(), TransferType.DEPOSIT, "Auter Account", accountNumber));
        return true;
    }

    @Transactional
    public boolean makeWithdraw(String accountNumber, BigDecimal amount) {
        BigDecimal withdrawAmount = amount.negate();
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if (accountService.checkBalance(account)) {
            if (accountService.getBalance(account).subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
                accountService.updateBalance(account, withdrawAmount);
                repository.save(new Transfer(LocalDateTime.now(), TransferType.WITHDRAW,
                        accountNumber, "external account"));
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean makeTransfer(String accountNumber1, String accountNumber2, BigDecimal amount) { //--> can be account number instead
        BigDecimal transferAmount = amount.negate();
        Account transfAccount = accountService.getAccountByAccountNumber(accountNumber1);
        Account rescvAccount = accountService.getAccountByAccountNumber(accountNumber2);

        if(accountService.checkBalance(transfAccount)){
            if(accountService.getBalance(transfAccount).subtract(amount).compareTo(BigDecimal.ZERO) >=0){
                accountService.updateBalance(transfAccount,transferAmount);
                accountService.updateBalance(rescvAccount,amount);
                repository.save(new Transfer(LocalDateTime.now(),TransferType.WIRED_TRANSFER,
                        transfAccount.getAccountNumber(),rescvAccount.getAccountNumber()));
                return true;
            }
        }
        return false;
    }

    /*
    //All should be transactional , maybe need user will turn out
    //Account managing capabilities later extracted into Bankmanager service:

    public Set<Transfer> listTransfers(){}   // by : date, period, intervall, type, currency, userID

    private BigDecimal bankBalance(){} // shows how much cash does Uncle Daughobert have

     private BigDecimal calculateExchangeRate(){} //--> via external api

    //maybe clash flow etc...
    */
}
