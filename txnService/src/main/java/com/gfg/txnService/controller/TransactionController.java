package com.gfg.txnService.controller;

import com.gfg.txnService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn-service")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/initiate/transaction")
    public String initiateTransaction(@RequestParam("amount") String amount,
                                      @RequestParam("receiver") String receiver,
                                      @RequestParam("purpose") String purpose){

        String sender = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Sender: "+sender);
        return transactionService.initiateTransaction(sender,receiver,amount,purpose);

    }
}
