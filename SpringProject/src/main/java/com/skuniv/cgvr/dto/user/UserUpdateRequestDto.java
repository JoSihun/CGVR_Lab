package com.skuniv.cgvr.dto.user;

import com.skuniv.cgvr.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String userId;    // ID
    private String korName;
    private String contact;
    private String email;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .korName(korName)
                .contact(contact)
                .email(email).build();
    }
}
