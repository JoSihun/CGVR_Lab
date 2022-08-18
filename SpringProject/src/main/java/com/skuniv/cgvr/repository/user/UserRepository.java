package com.skuniv.cgvr.repository.user;

import com.skuniv.cgvr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    User findByKorName(String korName);
    User findByEmail(String email);
}
