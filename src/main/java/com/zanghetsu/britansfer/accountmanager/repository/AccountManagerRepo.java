package com.zanghetsu.britansfer.accountmanager.repository;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountManagerRepo extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUserID(Long userId);
}
