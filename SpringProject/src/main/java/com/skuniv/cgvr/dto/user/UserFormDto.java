package com.skuniv.cgvr.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class UserFormDto {
    @NotEmpty(message = "아이디는 필수 입력 항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자리 이상, 16자리 이하로 입력해주세요.")
    private String password;


    @NotEmpty(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "연락처는 필수 입력 항목입니다.")
    private String contact;

    @Builder
    public UserFormDto(String username, String password,
                       String name, String email, String contact) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }
}
