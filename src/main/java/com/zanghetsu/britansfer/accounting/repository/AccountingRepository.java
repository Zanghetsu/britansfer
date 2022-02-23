package com.zanghetsu.britansfer.accounting.repository;

import com.zanghetsu.britansfer.accounting.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountingRepository extends JpaRepository<Transfer, UUID> {
    Optional<Transfer> findTransferById();

}
