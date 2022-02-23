package com.zanghetsu.britansfer.accountmanager.repository;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface AccountManagerRepo extends JpaRepository<Account,Long> {

    Optional<Account> getAccountByAcountNumber(String accountNumber);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
    Optional<Account> findAccountByUserID(Long userId);

    @Query("WHERE a.app_user_id like ?1")
    List<Account> getAllAccount(Long id);

    @Query("UPDATE account a SET a.balance = ?2 WHERE accountNumber = ?1")
    void updateBalance(String accountNumber, BigDecimal amount);



}
