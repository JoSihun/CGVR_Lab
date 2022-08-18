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
    private String userId;    // ID
    private String korName;
    private String contact;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(String userId, UserRole role,
                String korName, String email, String contact) {
        this.userId = userId;
        this.korName = korName;
        this.email = email;
        this.contact = contact;
        this.role = role;
    }

    public static User createUser(UserFormDto userFormDto) {
        return User.builder()
                .userId(userFormDto.getUserId())
                .korName(userFormDto.getKorName())
                .email(userFormDto.getEmail())
                .contact(userFormDto.getContact())
                .role(UserRole.USER)
                .build();
    }
}
