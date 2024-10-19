package com.gfg.walletService.repositories;

import com.gfg.walletService.models.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface WalletRepository extends JpaRepository<Wallet,Integer> {
    Wallet findByMobileNo(String mobile);

    @Transactional
    @Modifying
    @Query("update wallet w set w.balance=w.balance+:amount where w.mobileNo=:sender")
    void update(String sender,double amount);
}
