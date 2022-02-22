package com.zanghetsu.britansfer.registration.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDateTime dateOfBirth;
    private final String password;
}
