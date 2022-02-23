package com.zanghetsu.britansfer.accounting.repository;

import com.zanghetsu.britansfer.accounting.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface AccountingRepository extends JpaRepository<Transfer, UUID> {
    Optional<Transfer> findTransferById(String transferId);
}
