package com.zanghetsu.britansfer.accountmanager.repository;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;



@Repository
public interface AccountManagerRepo extends JpaRepository<Account,Long> {

    Optional<Account> getAccountByAccountNumber(String accountNumber);
    Optional<Account> findAccountByAccountNumber(String accountNumber);


    /*
    @Query("WHERE a.app_user_id like ?1")
    List<Account> getAllAccount(Long id);

    Optional<Account> findAccountByUserID(Long userId);
     */

    @Transactional
    @Modifying
    @Query("UPDATE Account  a SET a.balance = ?2 WHERE a.accountNumber = ?1")
    void updateBalance(String accountNumber, BigDecimal amount);



}
