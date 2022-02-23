package com.zanghetsu.britansfer.accountmanager.service;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import com.zanghetsu.britansfer.accountmanager.repository.AccountManagerRepo;
import com.zanghetsu.britansfer.appuser.entity.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountManagerRepo accMngrRepo;

    public String generateAccount(AppUser appUser){return null;}

    public BigDecimal getBalance(Long id, Account account){
        return null;
    }

    public Set<Account> getAccountsForUser(AppUser appUser){
        return null;
    }

    private boolean checkBalance(Account account){
        return false;
    } // --> currency types??

    public void updateBalance(Account account,BigDecimal amount){return;}  //---> deposit types to be enumerated??









}
