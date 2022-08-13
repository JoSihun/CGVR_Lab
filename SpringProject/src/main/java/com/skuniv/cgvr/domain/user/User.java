package com.skuniv.cgvr.domain.user;

import com.skuniv.cgvr.dto.user.UserFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // PK

    @Column(unique = true)
    private String username;    // ID
    private String password;    // PW

    private String name;
    private String contact;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(String username, String password, UserRole role,
                String name, String email, String contact) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.role = role;
    }

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(userFormDto.getUsername())
                .password(passwordEncoder.encode(userFormDto.getPassword()))
                .name(userFormDto.getName())
                .email(userFormDto.getEmail())
                .contact(userFormDto.getContact())
                .role(UserRole.USER)
                .build();
    }
}
