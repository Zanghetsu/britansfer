package com.zanghetsu.britansfer.accountmanager.repository;

import com.zanghetsu.britansfer.accountmanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountManagerRepo extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUserID(Long userId);
}
