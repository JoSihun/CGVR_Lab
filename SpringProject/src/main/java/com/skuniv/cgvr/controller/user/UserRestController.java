package com.skuniv.cgvr.controller.user;

import com.skuniv.cgvr.dto.user.UserSaveRequestDto;
import com.skuniv.cgvr.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    /* 관리자 등록 */
    @PostMapping("/admin/api")
    public Long adminAssign(@RequestBody UserSaveRequestDto requestDto) {
        System.out.println(requestDto.getUserId());
        return userService.regist(requestDto);
    }

    /* 관리자 제거 */
    @DeleteMapping("/admin/api/{id}")
    public Long adminDelete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
