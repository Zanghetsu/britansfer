package com.zanghetsu.britansfer.appuser.repository;

import com.zanghetsu.britansfer.appuser.entity.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
    Optional<AppUser> findByUserName(String userName);
}
