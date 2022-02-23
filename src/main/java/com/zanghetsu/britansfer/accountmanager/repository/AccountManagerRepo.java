package com.zanghetsu.britansfer.accountmanager.repository;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;


@Repository
public interface AccountManagerRepo extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUserID(Long userId);
    Optional<Account> findAccountByAccountNumber(String accountNumber);

    @Query("SELECT * FROM accounts WHERE user_id = id")
    Collection<? extends Account> getAllAccount(Long id);

    @Query("UPDATE account WHERE accountNumber")
    void updateBalance(String accountNumber, BigDecimal amount);

    Optional<Account>
}
