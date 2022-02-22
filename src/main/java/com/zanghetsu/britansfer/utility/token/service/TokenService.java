package com.zanghetsu.britansfer.utility.token.service;

import com.zanghetsu.britansfer.utility.token.entity.ConfirmationToken;
import com.zanghetsu.britansfer.utility.token.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public Optional<ConfirmationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void saveConfirmationToken(ConfirmationToken token){
        tokenRepository.save(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
