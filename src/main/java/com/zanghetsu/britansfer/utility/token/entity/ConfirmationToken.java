package com.zanghetsu.britansfer.utility.token.entity;

import com.zanghetsu.britansfer.appuser.entity.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @SequenceGenerator(name ="confirmation_sequence", sequenceName = "confirmation_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_sequence")
    private Long id;

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime creationTime;
    @Column(nullable = false)
    private LocalDateTime expirationTime;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(nullable = false,name = "app_user_id")
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime creationTime, LocalDateTime expirationTime, AppUser appUser) {
        this.token = token;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
        this.appUser = appUser;
    }
}
