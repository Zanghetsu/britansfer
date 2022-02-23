package com.zanghetsu.britansfer.accountmanager.entity;

import com.sun.istack.NotNull;
import com.zanghetsu.britansfer.appuser.entity.AppUser;
import com.zanghetsu.britansfer.appuser.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @SequenceGenerator(name = "account_sequence",sequenceName = "account_sequence",allocationSize = 1)
    @Id
    @GeneratedValue(generator = "account_sequence",strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime dateOfRegistry;
    private CurrencyType currencyType;

    @ManyToOne
    @JoinColumn(nullable = false,name = "app_user_id")
    private AppUser appUser;

    public Account(String accountNumber, BigDecimal balance, LocalDateTime dateOfRegistry, CurrencyType currencyType, AppUser appUser) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dateOfRegistry = dateOfRegistry;
        this.currencyType = currencyType;
        this.appUser = appUser;
    }
}
