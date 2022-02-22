package com.zanghetsu.britansfer.utility.token.repository;

import com.zanghetsu.britansfer.utility.token.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);
}
