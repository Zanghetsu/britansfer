package com.zanghetsu.britansfer.registration.service;

import com.zanghetsu.britansfer.appuser.entity.AppUser;
import com.zanghetsu.britansfer.appuser.entity.UserRole;
import com.zanghetsu.britansfer.appuser.service.AppUserService;
import com.zanghetsu.britansfer.utility.email.EmailValidator;
import com.zanghetsu.britansfer.registration.entity.RegistrationRequest;
import com.zanghetsu.britansfer.security.encrypter.PasswordEncrypter;
import com.zanghetsu.britansfer.utility.formatter.DateFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationService {


    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final PasswordEncrypter crypter;
    private final DateFormatter dateFormatter;

    //TODO:Inject account registration service -> generate accounts for user as Set<T> -> save / get user id for account generation process

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        Date dateOfBirth = dateFormatter.validator(request.getDateOfBirth());
        if(!isValidEmail){
            throw new IllegalStateException("The provided email is not valid!");
        }
        String encryptedPassword =crypter.bCryptPasswordEncoder().encode(request.getPassword());
        return appUserService.registerAppUserAccount(new AppUser(
                request.getUserName(), request.getFirstName(), request.getLastName(), request.getEmail(),
                dateOfBirth,encryptedPassword, UserRole.USER,true,true)
        );
    }
}
