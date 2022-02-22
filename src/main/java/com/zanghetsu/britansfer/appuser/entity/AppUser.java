package com.zanghetsu.britansfer.appuser.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AppUser implements UserDetails {


    @SequenceGenerator(name ="appUser_generator", sequenceName = "appUser_sequence", allocationSize = 1, initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUser_generator")
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime dateOfBirth;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;

    public AppUser(String userName, String firstName, String lastName, String email, LocalDateTime dateOfBirth, String password, UserRole appUserRole, Boolean locked, Boolean enabled) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
