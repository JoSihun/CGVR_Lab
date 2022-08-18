package com.skuniv.cgvr.dto.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.domain.user.UserRole;
import lombok.Getter;

@Getter
public class UserListResponseDto {
    private String userId;    // ID
    private String korName;
    private String contact;
    private String email;
    private UserRole role;

    public UserListResponseDto(User entity) {
        this.userId = entity.getUserId();
        this.korName = entity.getKorName();
        this.contact = entity.getContact();
        this.email = entity.getEmail();
        this.role = entity.getRole();
    }
}
