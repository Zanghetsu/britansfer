package com.zanghetsu.britansfer.appuser.service;

import com.zanghetsu.britansfer.appuser.entity.AppUser;
import com.zanghetsu.britansfer.appuser.repository.AppUserRepository;
import com.zanghetsu.britansfer.security.encrypter.PasswordEncrypter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "User with name %s not found!";
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,username)));
    }

    public String registerAppUserAccount(AppUser appUser){
        boolean userExists = appUserRepository.findByUserName(appUser.getUsername()).isPresent();

        if(userExists){
           throw new IllegalStateException("this username is already taken!");
        }
        appUserRepository.save(appUser);

        //TODO:send confirmation token
            return "it gone trough";
    }
}
