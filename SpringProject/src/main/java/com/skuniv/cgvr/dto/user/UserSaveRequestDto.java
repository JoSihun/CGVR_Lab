package com.skuniv.cgvr.dto.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.domain.user.UserRole;
import lombok.Data;

@Data
public class UserSaveRequestDto {
    private String userId;    // ID
    private String korName;
    private String contact;
    private String email;
    private UserRole role;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .korName(korName)
                .contact(contact)
                .email(email)
                .role(role)
                .build();
    }
}
