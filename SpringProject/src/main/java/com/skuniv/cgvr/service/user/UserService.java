package com.skuniv.cgvr.service.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        User findUser = this.userRepository.findByUsername(user.getUsername());
        if (findUser != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        return this.userRepository.save(user);
    }

}
