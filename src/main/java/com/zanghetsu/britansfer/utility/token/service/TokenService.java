package com.zanghetsu.britansfer.utility.token.service;

import com.zanghetsu.britansfer.utility.token.entity.ConfirmationToken;
import com.zanghetsu.britansfer.utility.token.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        tokenRepository.save(token);
    }
}
