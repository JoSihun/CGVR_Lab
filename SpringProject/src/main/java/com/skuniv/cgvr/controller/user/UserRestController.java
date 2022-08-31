package com.skuniv.cgvr.controller.user;

import com.skuniv.cgvr.dto.user.UserSaveRequestDto;
import com.skuniv.cgvr.dto.user.UserUpdateRequestDto;
import com.skuniv.cgvr.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    /* 관리자 등록 */
    @PostMapping("/admin/api")
    public Long adminGrant(@RequestBody UserSaveRequestDto requestDto) {
        return userService.grant(requestDto);
    }

    /* 관리자 수정 */
    @PutMapping("/admin/api/{id}")
    public Long adminUpdate(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    /* 관리자 제거 */
    @DeleteMapping("/admin/api/{id}")
    public Long adminRevoke(@PathVariable Long id) {
        userService.revoke(id);
        return id;
    }
}
