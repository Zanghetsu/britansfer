package com.zanghetsu.britansfer.accounting.service;

import com.zanghetsu.britansfer.accounting.entity.Transfer;
import com.zanghetsu.britansfer.accountmanager.entity.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class TransferService {

    public boolean makeDeposit(Account account1, BigDecimal amount){}
    public boolean makeWithdraw(Account account1,BigDecimal amount){}
    public boolean makeTransfer(Account account1, Account account2,BigDecimal amount) //--> can be account number instead
    private BigDecimal calculateExchangeRate(){} //--> via external api

    //All should be transactional , maybe need user will turn out

    //Account managing capabilities later extracted into Bankmanager service:

    public Set<Transfer> listTransfers(){}   // by : date, period, intervall, type, currency, userID
    private BigDecimal bankBalance(){} // shows how much cash does Uncle Daughobert have

    //maybe clash flow etc...

}
