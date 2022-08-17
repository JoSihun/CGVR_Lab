package com.skuniv.cgvr.repository.user;

import com.skuniv.cgvr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByName(String name);
    User findByEmail(String email);
}
