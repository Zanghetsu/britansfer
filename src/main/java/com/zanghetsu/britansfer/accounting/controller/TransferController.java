package com.zanghetsu.britansfer.accounting.controller;


import com.zanghetsu.britansfer.accounting.controller.transferRequest.TransferRequest;
import com.zanghetsu.britansfer.accounting.service.TransferService;
import com.zanghetsu.britansfer.accountmanager.entity.Account;
import com.zanghetsu.britansfer.accountmanager.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1/transaction")
@AllArgsConstructor
public class TransferController {


    private TransferService transferService;
    private AccountService accountService;

    @PostMapping(path = "deposit")
    public String depositToAccount(@RequestBody TransferRequest request){
        if(transferService.makeDeposit(request.getAccountNumber1(), BigDecimal.valueOf(request.getAmount()))){
            return "Successful Deposit!";
        }
        return "Deposit did not happen, check valid deposit amount and account number!";
    }

    @PostMapping(path = "withdraw")
    public String withdrawFromAccount(@RequestBody TransferRequest request){
        if(transferService.makeWithdraw(request.getAccountNumber1(),BigDecimal.valueOf(request.getAmount()))){
            return "Successful Withdraw";
        }
        return "Withdraw cannot be made, due to lack of balance or invalid amount request!";
    }

    @PostMapping(path = "wiretransfer")
    public String wireTransfer(@RequestBody TransferRequest request){
        if(transferService.makeTransfer(request.getAccountNumber1(),request.getAccountNumber2(),BigDecimal.valueOf(request.getAmount()))){
            return "Successful transfer!";
        }
        return "Transaction canceled, not enough balance to make transfer, or invalid amount was given!";
    }

    @GetMapping(path = "balance-info")
    public String getAccountBalance(@RequestBody TransferRequest request){
        Account account = accountService.getAccountByAccountNumber(request.getAccountNumber1());
        return accountService.getBalance(account).toString();
    }

}
