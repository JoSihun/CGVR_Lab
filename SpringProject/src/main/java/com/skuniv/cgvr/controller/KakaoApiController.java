package com.skuniv.cgvr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoApiController {
    // 이거 경로를 /***/*** 이런식으로 바꾸면 암만 해도 매핑이 안됩니다..
    @GetMapping("/kakao")
    public String kakaoApi() {
        return "kakao_api.html";
    }
}
