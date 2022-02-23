package com.zanghetsu.britansfer.accounting.controller;


import com.zanghetsu.britansfer.accounting.service.TransferService;
import com.zanghetsu.britansfer.accountmanager.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/transaction")
@AllArgsConstructor
public class TransferController {

    private TransferService transferService;
    private AccountService accountService;

    @PostMapping(path = "deposit")
    public String depositToAccount()

    @PostMapping(path = "withdraw")
    public String withdrawFromAccount()

    @PostMapping(path = "wiretransfer")
    public String wireTransfare()

    @GetMapping(path = "balance-info")
    public String getAccountBalance()

}
