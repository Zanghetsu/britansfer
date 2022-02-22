package com.zanghetsu.britansfer.registration.service;

import com.zanghetsu.britansfer.registration.entity.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "functioning!";
    }
}
