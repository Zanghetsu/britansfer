package com.zanghetsu.britansfer.registration.service;

import com.zanghetsu.britansfer.appuser.entity.AppUser;
import com.zanghetsu.britansfer.appuser.entity.UserRole;
import com.zanghetsu.britansfer.appuser.service.AppUserService;
import com.zanghetsu.britansfer.utility.email.EmailValidator;
import com.zanghetsu.britansfer.registration.entity.RegistrationRequest;
import com.zanghetsu.britansfer.security.encrypter.PasswordEncrypter;
import com.zanghetsu.britansfer.utility.formatter.DateFormatter;
import com.zanghetsu.britansfer.utility.token.entity.ConfirmationToken;
import com.zanghetsu.britansfer.utility.token.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationService {


    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final PasswordEncrypter crypter;
    private final DateFormatter dateFormatter;
    private final TokenService tokenService;

    //TODO:Inject account registration service -> generate accounts for user as Set<T> -> save / get user id for account generation process

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        Date dateOfBirth = dateFormatter.validator(request.getDateOfBirth());
        if (!isValidEmail) {
            throw new IllegalStateException("The provided email is not valid!");
        }
        String encryptedPassword = crypter.bCryptPasswordEncoder().encode(request.getPassword());

        return appUserService.registerAppUserAccount(new AppUser(
                request.getUserName(), request.getFirstName(), request.getLastName(), request.getEmail(),
                dateOfBirth, encryptedPassword, UserRole.USER, true, true)
        );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("No token FOUND!!!"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("This email has already been confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpirationTime();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("This token has expired!");
        }

        tokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }
}

